// This file is part of MicropolisJ.
// Copyright (C) 2013 Jason Long
// Portions Copyright (C) 1989-2007 Electronic Arts Inc.
//
// MicropolisJ is free software; you can redistribute it and/or modify
// it under the terms of the GNU GPLv3, with additional terms.
// See the README file, included in this distribution, for details.

package micropolisj.engine;

import static micropolisj.engine.TileConstants.*;

class RoadLikeTool extends ToolStroke
{
	RoadLikeTool(Micropolis city, MicropolisTool tool, int xpos, int ypos)
	{
		super(city, tool, xpos, ypos);
	}

	@Override
	protected void applyArea(ToolEffectIfc eff)
	{
		for (;;) {
			if (!applyForward(eff)) {
				break;
			}
			if (!applyBackward(eff)) {
				break;
			}
		}
	}

	boolean applyBackward(ToolEffectIfc eff)
	{
		boolean anyChange = false;

		CityRect b = getBounds();
		for (int i = b.height - 1; i >= 0; i--) {
			for (int j = b.width - 1; j >= 0; j--) {
				TranslatedToolEffect tte = new TranslatedToolEffect(eff, b.x+j, b.y+i);
				anyChange = anyChange || applySingle(tte);
			}
		}
		return anyChange;
	}

	boolean applyForward(ToolEffectIfc eff)
	{
		boolean anyChange = false;

		CityRect b = getBounds();
		for (int i = 0; i < b.height; i++) {
			for (int j = 0; j < b.width; j++) {
				TranslatedToolEffect tte = new TranslatedToolEffect(eff, b.x+j, b.y+i);
				anyChange = anyChange || applySingle(tte);
			}
		}
		return anyChange;
	}

	@Override
	public CityRect getBounds()
	{
		// constrain bounds to be a rectangle with
		// either width or height equal to one.

		assert tool.getWidth() == 1;
		assert tool.getHeight() == 1;

		if (Math.abs(xdest-xpos) >= Math.abs(ydest-ypos)) {
			// horizontal line
			CityRect r = new CityRect();
			r.x = Math.min(xpos, xdest);
			r.width = Math.abs(xdest-xpos) + 1;
			r.y = ypos;
			r.height = 1;
			return r;
		}
		else {
			// vertical line
			CityRect r = new CityRect();
			r.x = xpos;
			r.width = 1;
			r.y = Math.min(ypos, ydest);
			r.height = Math.abs(ydest-ypos) + 1;
			return r;
		}
	}

	boolean applySingle(ToolEffectIfc eff)
	{
		switch (tool)
		{
		case RAIL:
			return applyRailTool(eff);

		case ROADS:
			return applyRoadTool(eff);

		case WIRE:
			return applyWireTool(eff);

		default:
			throw new Error("Unexpected tool: " + tool);
		}
	}

	boolean applyRailTool(ToolEffectIfc eff)
	{
		if (layRail(eff)) {
			fixZone(eff);
			return true;
		}
		else {
			return false;
		}
	}

	boolean applyRoadTool(ToolEffectIfc eff)
	{
		if (layRoad(eff)) {
			fixZone(eff);
			return true;
		}
		else {
			return false;
		}
	}

	boolean applyWireTool(ToolEffectIfc eff)
	{
		if (layWire(eff)) {
			fixZone(eff);
			return true;
		}
		else {
			return false;
		}
	}

	private boolean layRail(ToolEffectIfc eff)
	{
		final int RAIL_COST = 20;
		final int TUNNEL_COST = 100;

		int cost = RAIL_COST;

		char tile = (char) eff.getTile(0, 0);
		tile = neutralizeRoad(tile);

		switch (tile)
		{
		case RIVER:		// rail on water
		case R_EDGE:
		case CHANNEL:

			cost = TUNNEL_COST;

			// check east
			{
				char eTile = neutralizeRoad(eff.getTile(1, 0));
				if (eTile == RAIL_H_POWER_V ||
					eTile == H_RAIL ||
					(eTile >= LH_RAIL && eTile <= H_RAILROAD))
				{
					eff.setTile(0, 0, H_RAIL);
					break;
				}
			}

			// check west
			{
				char wTile = neutralizeRoad(eff.getTile(-1, 0));
				if (wTile == RAIL_H_POWER_V ||
					wTile == H_RAIL ||
					(wTile > V_RAIL && wTile < V_RAILROAD))
				{
					eff.setTile(0, 0, H_RAIL);
					break;
				}
			}

			// check south
			{
				char sTile = neutralizeRoad(eff.getTile(0, 1));
				if (sTile == RAIL_V_POWER_H ||
					sTile == V_RAILROAD ||
					(sTile > H_RAIL && sTile < H_RAILROAD))
				{
					eff.setTile(0, 0, V_RAIL);
					break;
				}
			}

			// check north
			{
				char nTile = neutralizeRoad(eff.getTile(0, -1));
				if (nTile == RAIL_V_POWER_H ||
					nTile == V_RAILROAD ||
					(nTile > H_RAIL && nTile < H_RAILROAD))
				{
					eff.setTile(0, 0, V_RAIL);
					break;
				}
			}

			// cannot do road here
			return false;

		case LH_POWER: // rail on power
			eff.setTile(0, 0, RAIL_V_POWER_H);
			break;

		case LV_POWER: // rail on power
			eff.setTile(0, 0, RAIL_H_POWER_V);
			break;

		case TileConstants.ROADS:	// rail on road (case 1)
			eff.setTile(0, 0, V_RAILROAD);
			break;

		case ROADS_2:	// rail on road (case 2)
			eff.setTile(0, 0, H_RAILROAD);
			break;

		default:
			if (tile != DIRT) {
				if (city.autoBulldoze && canAutoBulldozeRRW(tile)) {
					cost += 1; //autodoze cost
				}
				else {
					// cannot do rail here
					return false;
				}
			}

		  	//rail on dirt
			eff.setTile(0, 0, LH_RAIL);
			break;
		}

		eff.spend(cost);
		return true;
	}

	private boolean layRoad(ToolEffectIfc eff)
	{
		final int ROAD_COST = 10;
		final int BRIDGE_COST = 50;

		int cost = ROAD_COST;

		char tile = (char) eff.getTile(0, 0);
		switch (tile)
		{
		case RIVER:		// road on water
		case R_EDGE:
		case CHANNEL:	// check how to build bridges, if possible.

			cost = BRIDGE_COST;

			// check east
			{
				char eTile = neutralizeRoad(eff.getTile(1, 0));
				if (eTile == V_RAILROAD ||
					eTile == HB_RIDGE ||
					(eTile >= TileConstants.ROADS && eTile <= H_ROAD_POWER))
				{
					eff.setTile(0, 0, HB_RIDGE);
					break;
				}
			}

			// check west
			{
				char wTile = neutralizeRoad(eff.getTile(-1, 0));
				if (wTile == V_RAILROAD ||
					wTile == HB_RIDGE ||
					(wTile >= TileConstants.ROADS && wTile <= INTERSECTION))
				{
					eff.setTile(0, 0, HB_RIDGE);
					break;
				}
			}

			// check south
			{
				char sTile = neutralizeRoad(eff.getTile(0, 1));
				if (sTile == H_RAILROAD ||
					sTile == V_ROAD_POWER ||
					(sTile >= VB_RIDGE && sTile <= INTERSECTION))
				{
					eff.setTile(0, 0, VB_RIDGE);
					break;
				}
			}

			// check north
			{
				char nTile = neutralizeRoad(eff.getTile(0, -1));
				if (nTile == H_RAILROAD ||
					nTile == V_ROAD_POWER ||
					(nTile >= VB_RIDGE && nTile <= INTERSECTION))
				{
					eff.setTile(0, 0, VB_RIDGE);
					break;
				}
			}

			// cannot do road here
			return false;

		case LH_POWER: //road on power
			eff.setTile(0, 0, V_ROAD_POWER);
			break;

		case LV_POWER: //road on power #2
			eff.setTile(0, 0, H_ROAD_POWER);
			break;

		case LH_RAIL: //road on rail
			eff.setTile(0, 0, H_RAILROAD);
			break;

		case LV_RAIL: //road on rail #2
			eff.setTile(0, 0, V_RAILROAD);
			break;

		default:
			if (tile != DIRT) {
				if (city.autoBulldoze && canAutoBulldozeRRW(tile)) {
					cost += 1; //autodoze cost
				}
				else {
					// cannot do road here
					return false;
				}
			}

			// road on dirt;
			// just build a plain road, fixZone will fix it.
			eff.setTile(0, 0, TileConstants.ROADS);
			break;
		}
	
		eff.spend(cost);
		return true;
	}

	private boolean layWire(ToolEffectIfc eff)
	{
		final int WIRE_COST = 5;
		final int UNDERWATER_WIRE_COST = 25;

		int cost = WIRE_COST;

		char tile = (char) eff.getTile(0, 0);
		tile = neutralizeRoad(tile);

		switch (tile)
		{
		case RIVER:		// wire on water
		case R_EDGE:
		case CHANNEL:

			cost = UNDERWATER_WIRE_COST;

			// check east
			{
				int tmp = eff.getTile(1, 0);
				char tmpn = neutralizeRoad(tmp);

				if (isConductive(tmp) &&
					tmpn != H_ROAD_POWER &&
					tmpn != RAIL_H_POWER_V &&
					tmpn != H_POWER)
				{
					eff.setTile(0, 0, V_POWER);
					break;
				}
			}

			// check west
			{
				int tmp = eff.getTile(-1, 0);
				char tmpn = neutralizeRoad(tmp);

				if (isConductive(tmp) &&
					tmpn != H_ROAD_POWER &&
					tmpn != RAIL_H_POWER_V &&
					tmpn != H_POWER)
				{
					eff.setTile(0, 0, V_POWER);
					break;
				}
			}

			// check south
			{
				int tmp = eff.getTile(0, 1);
				char tmpn = neutralizeRoad(tmp);

				if (isConductive(tmp) &&
					tmpn != V_ROAD_POWER &&
					tmpn != RAIL_V_POWER_H &&
					tmpn != V_POWER)
				{
					eff.setTile(0, 0, H_POWER);
					break;
				}
			}

			// check north
			{
				int tmp = eff.getTile(0, -1);
				char tmpn = neutralizeRoad(tmp);

				if (isConductive(tmp) &&
					tmpn != V_ROAD_POWER &&
					tmpn != RAIL_V_POWER_H &&
					tmpn != V_POWER)
				{
					eff.setTile(0, 0, H_POWER);
					break;
				}
			}

			// cannot do wire here
			return false;

		case TileConstants.ROADS: // wire on E/W road
			eff.setTile(0, 0, H_ROAD_POWER);
			break;

		case ROADS_2: // wire on N/S road
			eff.setTile(0, 0, V_ROAD_POWER);
			break;

		case LH_RAIL:	// wire on E/W railroad tracks
			eff.setTile(0, 0, RAIL_H_POWER_V);
			break;

		case LV_RAIL:	// wire on N/S railroad tracks
			eff.setTile(0, 0, RAIL_V_POWER_H);
			break;

		default:
			if (tile != DIRT) {
				if (city.autoBulldoze && canAutoBulldozeRRW(tile)) {
					cost += 1; //autodoze cost
				}
				else {
					//cannot do wire here
					return false;
				}
			}

			//wire on dirt
			eff.setTile(0, 0, LH_POWER);
			break;
		}

		eff.spend(cost);
		return true;
	}
}
