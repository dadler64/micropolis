// This file is part of MicropolisJ.
// Copyright (C) 2013 Jason Long
// Portions Copyright (C) 1989-2007 Electronic Arts Inc.
//
// MicropolisJ is free software; you can redistribute it and/or modify
// it under the terms of the GNU GPLv3, with additional terms.
// See the README file, included in this distribution, for details.

package micropolisj.engine;

/**
 * Contains symbolic names of certain tile values,
 * and helper functions to test tile attributes.
 * Attributes of tiles that are interesting:
 * <ul>
 * <li>ZONE - the special tile for a zone
 * <li>ANIM - the tile animates
 * <li>BULL - is bulldozable
 * <li>BURN - is combustible
 * <li>COND - can conduct power
 * <li>Road - traffic
 * <li>Rail - railroad
 * <li>Floodable - subject to floods
 * <li>Wet
 * <li>Rubble
 * <li>Tree
 * <li>OverWater
 * <li>Arsonable
 * <li>Vulnerable - vulnerable to earthquakes
 * <li>Bridge
 * <li>AutoDozeRRW - automatically bulldoze when placing Road/Rail/Wire
 * <li>AutoDozeZ - automatically bulldoze when placing Zone
 * </ul>
 */
public class TileConstants {

    //
    // terrain mapping
    //
    public static final short CLEAR = -1;
    public static final char DIRT = 0;
    public static final char LIGHTNING_BOLT = 827;
    public static final char LAST_TILE = 956;
    //
    // status bits
    //
    public static final char POWER_BIT = 32768;  // bit 15 ... currently powered
    public static final char ALL_BITS = 64512;   // mask for upper 6 bits
    public static final char LOW_MASK = 1023; //mask for low 10 bits
    static final char RIVER = 2;
    static final char R_EDGE = 3;
    static final char CHANNEL = 4;
    static final char RIVER_EDGE = 5;
    static final char FIRST_RIVER_EDGE = 5;
    static final char LAST_RIVER_EDGE = 20;
    static final char TREE_BASE = 21;
    static final char WOODS_LOW = TREE_BASE;
    static final char WOODS = 37;
    static final char WOODS_HIGH = 39;
    static final char WOODS_2 = 40;
    static final char WOODS_5 = 43;
    static final char RUBBLE = 44;
    static final char LAST_RUBBLE = 47;
    static final char FLOOD = 48;
    static final char LAST_FLOOD = 51;
    static final char RADIATED_TILE = 52;
    static final char FIRE = 56;
    static final char ROAD_BASE = 64;
    static final char HB_RIDGE = 64;
    static final char VB_RIDGE = 65;
    static final char ROADS = 66;
    static final char ROADS_2 = 67;
    static final char INTERSECTION = 76;
    static final char H_ROAD_POWER = 77;
    static final char V_ROAD_POWER = 78;
    static final char BRWH = 79;       //horz bridge, open
    static final char LTRF_BASE = 80;
    static final char BRWV = 95;       //vert bridge, open
    static final char HTRF_BASE = 144;
    static final char POWER_BASE = 208;
    static final char H_POWER = 208;    //underwater power-line
    static final char V_POWER = 209;
    static final char LH_POWER = 210;
    static final char LV_POWER = 211;
    static final char LV_POWER_2 = 212;
    static final char RAIL_H_POWER_V = 221;
    static final char RAIL_V_POWER_H = 222;
    static final char LAST_POWER = 222;
    static final char RAIL_BASE = 224;
    static final char H_RAIL = 224;     //underwater rail (horz)
    static final char V_RAIL = 225;     //underwater rail (vert)
    static final char LH_RAIL = 226;
    static final char LV_RAIL = 227;
    static final char LV_RAIL_2 = 228;
    static final char H_RAILROAD = 237;
    static final char V_RAILROAD = 238;
    static final char LAST_RAIL = 238;
    static final char RESIDENTIAL_BASE = 240;
    static final char RESIDENTIAL_CLEAR = 244;
    static final char HOUSE = 249;
    static final char LHTHR = 249;  //12 house tiles
    static final char HHTHR = 260;
    static final char RESIDENTIAL_ZONE_BASE = 265; //residential zone base
    static final char HOSPITAL = 409;
    static final char CHURCH = 418;
    static final char COMMERCIAL_BASE = 423;
    static final char COMMERCIAL_CLEAR = 427;
    static final char COMMERCIAL_ZONE_BASE = 436; //commercial zone base
    static final char INDUSTRIAL_BASE = 612;
    static final char INDUSTRIAL_CLEAR = 616;
    static final char INDUSTRIAL_ZONE_BASE = 625;
    static final char PORT_BASE = 693;
    static final char PORT = 698;
    static final char AIRPORT = 716;
    static final char POWER_PLANT = 750;
    static final char FIRE_STATION = 765;
    static final char POLICE_STATION = 774;
    static final char STADIUM = 784;
    static final char FULL_STADIUM = 800;
    static final char NUCLEAR = 816;
    static final char LAST_ZONE = 826;
    static final char H_BRIDGE_0 = 828;   //draw bridge tiles (horz)
    static final char H_BRIDGE_1 = 829;
    static final char H_BRIDGE_2 = 830;
    static final char H_BRIDGE_3 = 831;
    static final char FOUNTAIN = 840;
    static final char TINY_EXP = 860;
    static final char FOOTBALL_GAME_1 = 932;
    static final char FOOTBALL_GAME_2 = 940;
    static final char V_BRIDGE_0 = 948;   //draw bridge tiles (vert)
    static final char V_BRIDGE_1 = 949;
    static final char V_BRIDGE_2 = 950;
    static final char V_BRIDGE_3 = 951;
    private static final char ROADS_3 = 68;
    private static final char ROADS_4 = 69;
    private static final char ROADS_5 = 70;
    private static final char ROADS_6 = 71;
    private static final char ROADS_7 = 72;
    private static final char ROADS_8 = 73;
    private static final char ROADS_9 = 74;
    private static final char ROADS_10 = 75;
    static final char[] ROAD_TABLE = new char[]{
            ROADS, ROADS_2, ROADS, ROADS_3,
            ROADS_2, ROADS_2, ROADS_4, ROADS_8,
            ROADS, ROADS_6, ROADS, ROADS_7,
            ROADS_5, ROADS_10, ROADS_9, INTERSECTION
    };
    private static final char LAST_ROAD = 206;
    private static final char LV_POWER_3 = 213;
    private static final char LV_POWER_4 = 214;
    private static final char LV_POWER_5 = 215;
    private static final char LV_POWER_6 = 216;
    private static final char LV_POWER_7 = 217;
    private static final char LV_POWER_8 = 218;
    private static final char LV_POWER_9 = 219;
    private static final char LV_POWER_10 = 220;
    static final char[] WIRE_TABLE = new char[]{
            LH_POWER, LV_POWER, LH_POWER, LV_POWER_2,
            LV_POWER, LV_POWER, LV_POWER_3, LV_POWER_7,
            LH_POWER, LV_POWER_5, LH_POWER, LV_POWER_6,
            LV_POWER_4, LV_POWER_9, LV_POWER_8, LV_POWER_10
    };
    private static final char LV_RAIL_3 = 229;
    private static final char LV_RAIL_4 = 230;
    private static final char LV_RAIL_5 = 231;
    private static final char LV_RAIL_6 = 232;
    private static final char LV_RAIL_7 = 233;
    private static final char LV_RAIL_8 = 234;
    private static final char LV_RAIL_9 = 235;
    private static final char LV_RAIL_10 = 236;
    // bit 14 ... unused
    // bit 13 ... unused
    // bit 12 ... unused
    // bit 11 ... unused
    // bit 10 ... unused
    static final char[] RAIL_TABLE = new char[]{
            LH_RAIL, LV_RAIL, LH_RAIL, LV_RAIL_2,
            LV_RAIL, LV_RAIL, LV_RAIL_3, LV_RAIL_7,
            LH_RAIL, LV_RAIL_5, LH_RAIL, LV_RAIL_6,
            LV_RAIL_4, LV_RAIL_9, LV_RAIL_8, LV_RAIL_10
    };
    private static final char LAST_TINY_EXP = 867;

    private TileConstants() {
    }

    /**
     * Checks whether the tile can be auto-bulldozed for
     * placement of road, rail, or wire.
     */
    public static boolean canAutoBulldozeRRW(int tileValue) {
        // can we auto-bulldoze this tile?
        return (
                (tileValue >= FIRST_RIVER_EDGE && tileValue <= LAST_RUBBLE) ||
                        (tileValue >= TINY_EXP && tileValue <= LAST_TINY_EXP)
        );
    }

    /**
     * Checks whether the tile can be auto-bulldozed for
     * placement of a zone.
     */
    public static boolean canAutoBulldozeZ(char tileValue) {
        //FIXME- what is significance of POWERBASE+2 and POWERBASE+12 ?

        // can we auto-bulldoze this tile?
	    return (tileValue >= FIRST_RIVER_EDGE && tileValue <= LAST_RUBBLE) ||
			    (tileValue >= POWER_BASE + 2 && tileValue <= POWER_BASE + 12) ||
			    (tileValue >= TINY_EXP && tileValue <= LAST_TINY_EXP);
    }

    //used by scanTile
    public static String getTileBehavior(int tile) {
        assert (tile & LOW_MASK) == tile;

        TileSpec tileSpec = Tiles.get(tile);
        return tileSpec != null ? tileSpec.getAttribute("behavior") : null;
    }

    //used by queryZoneStatus
    public static int getDescriptionNumber(int tile) {
        assert (tile & LOW_MASK) == tile;

        TileSpec tileSpec = Tiles.get(tile);
        if (tileSpec != null) {
            return tileSpec.getDescriptionNumber();
        } else {
            return -1;
        }
    }

    public static int getPollutionValue(int tile) {
        assert (tile & LOW_MASK) == tile;

        TileSpec spec = Tiles.get(tile);
        return spec != null ? spec.getPollutionValue() : 0;
    }

    public static boolean isAnimated(int tile) {
        assert (tile & LOW_MASK) == tile;

        TileSpec spec = Tiles.get(tile);
        return spec != null && spec.animNext != null;
    }

    //used by setFire()
    public static boolean isArsonable(int tile) {
        assert (tile & LOW_MASK) == tile;

        return (
                !isZoneCenter(tile) &&
                        tile >= LHTHR &&
                        tile <= LAST_ZONE
        );
    }

    //used by Sprite::destroyTile
    public static boolean isBridge(int tile) {
        return isRoad(tile) && !isCombustible(tile);
    }

    public static boolean isCombustible(int tile) {
        assert (tile & LOW_MASK) == tile;

        TileSpec spec = Tiles.get(tile);
        return spec != null && spec.canBurn;
    }

    public static boolean isConductive(int tile) {
        assert (tile & LOW_MASK) == tile;

        TileSpec spec = Tiles.get(tile);
        return spec != null && spec.canConduct;
    }

    /**
     * Used in repairZone().
     */
    public static boolean isIndestructible(int tile) {
        assert (tile & LOW_MASK) == tile;

        return tile >= RUBBLE && tile < ROAD_BASE;
    }

    /**
     * Used in zonePlop().
     */
    public static boolean isIndestructible2(int tile) {
        assert (tile & LOW_MASK) == tile;

        return tile >= FLOOD && tile < ROAD_BASE;
    }

    public static boolean isOverWater(int tile) {
        assert (tile & LOW_MASK) == tile;

        TileSpec spec = Tiles.get(tile);
        return spec != null && spec.overWater;
    }

    public static boolean isRubble(int tile) {
        assert (tile & LOW_MASK) == tile;

        return ((tile >= RUBBLE) &&
                (tile <= LAST_RUBBLE));
    }

    public static boolean isTree(char tile) {
        assert (tile & LOW_MASK) == tile;

        return ((tile >= WOODS_LOW) &&
                (tile <= WOODS_HIGH));
    }

    //used by makeEarthquake
    public static boolean isVulnerable(int tile) {
        assert (tile & LOW_MASK) == tile;

	    return tile >= RESIDENTIAL_BASE &&
			    tile <= LAST_ZONE &&
			    !isZoneCenter(tile);
    }

    public static boolean checkWet(int tile) {
        assert (tile & LOW_MASK) == tile;

        return (tile == POWER_BASE ||
                tile == POWER_BASE + 1 ||
                tile == RAIL_BASE ||
                tile == RAIL_BASE + 1 ||
                tile == BRWH ||
                tile == BRWV);
    }

    public static CityDimension getZoneSizeFor(int tile) {
        assert isZoneCenter(tile);
        assert (tile & LOW_MASK) == tile;

        TileSpec spec = Tiles.get(tile);
        return spec != null ? spec.getBuildingSize() : null;
    }

    public static boolean isConstructed(int tile) {
        assert (tile & LOW_MASK) == tile;

        return tile >= 0 && tile >= ROAD_BASE;
    }

    static boolean isRiverEdge(int tile) {
        assert (tile & LOW_MASK) == tile;

        return tile >= FIRST_RIVER_EDGE && tile <= LAST_RIVER_EDGE;
    }

    public static boolean isDozeable(int tile) {
        assert (tile & LOW_MASK) == tile;

        TileSpec spec = Tiles.get(tile);
        return spec != null && spec.canBulldoze;
    }

    static boolean isFloodable(int tile) {
        assert (tile & LOW_MASK) == tile;

        return (tile == DIRT || (isDozeable(tile) && isCombustible(tile)));
    }

    /**
     * Note: does not include rail/road tiles.
     *
     * @see #isRoadAny
     */
    public static boolean isRoad(int tile) {
        assert (tile & LOW_MASK) == tile;

        return (tile >= ROAD_BASE && tile < POWER_BASE);
    }

    public static boolean isRoadAny(int tile) {
        assert (tile & LOW_MASK) == tile;

        return (tile >= ROAD_BASE && tile < POWER_BASE)
                || (tile == H_RAILROAD)
                || (tile == V_RAILROAD);
    }

    /**
     * Checks whether the tile is a road that will automatically change to connect to
     * neighboring roads.
     */
    public static boolean isRoadDynamic(int tile) {
        int tmp = neutralizeRoad(tile);
        return (tmp >= ROADS && tmp <= INTERSECTION);
    }

    public static boolean roadConnectsEast(int tile) {
        tile = neutralizeRoad(tile);
        return (((tile == V_RAILROAD) ||
                (tile >= ROAD_BASE && tile <= V_ROAD_POWER)
        ) &&
                (tile != V_ROAD_POWER) &&
                (tile != H_RAILROAD) &&
                (tile != VB_RIDGE));
    }

    public static boolean roadConnectsNorth(int tile) {
        tile = neutralizeRoad(tile);
        return (((tile == H_RAILROAD) ||
                (tile >= ROAD_BASE && tile <= V_ROAD_POWER)
        ) &&
                (tile != H_ROAD_POWER) &&
                (tile != V_RAILROAD) &&
                (tile != ROAD_BASE));
    }

    public static boolean roadConnectsSouth(int tile) {
        tile = neutralizeRoad(tile);
        return (((tile == H_RAILROAD) ||
                (tile >= ROAD_BASE && tile <= V_ROAD_POWER)
        ) &&
                (tile != H_ROAD_POWER) &&
                (tile != V_RAILROAD) &&
                (tile != ROAD_BASE));
    }

    public static boolean roadConnectsWest(int tile) {
        tile = neutralizeRoad(tile);
        return (((tile == V_RAILROAD) ||
                (tile >= ROAD_BASE && tile <= V_ROAD_POWER)
        ) &&
                (tile != V_ROAD_POWER) &&
                (tile != H_RAILROAD) &&
                (tile != VB_RIDGE));
    }

    public static boolean isRail(int tile) {
        assert (tile & LOW_MASK) == tile;

        return (tile >= RAIL_BASE && tile < RESIDENTIAL_BASE);
    }

    public static boolean isRailAny(int tile) {
        assert (tile & LOW_MASK) == tile;

        return (tile >= RAIL_BASE && tile < RESIDENTIAL_BASE)
                || (tile == RAIL_H_POWER_V)
                || (tile == RAIL_V_POWER_H);
    }

    public static boolean isRailDynamic(int tile) {
        assert (tile & LOW_MASK) == tile;

        return (tile >= LH_RAIL && tile <= LV_RAIL_10);
    }

    public static boolean railConnectsEast(int tile) {
        tile = neutralizeRoad(tile);
        return (tile >= RAIL_H_POWER_V && tile <= V_RAILROAD &&
                tile != RAIL_V_POWER_H &&
                tile != V_RAILROAD &&
                tile != V_RAIL);
    }

    public static boolean railConnectsNorth(int tile) {
        tile = neutralizeRoad(tile);
        return (tile >= RAIL_H_POWER_V && tile <= V_RAILROAD &&
                tile != RAIL_H_POWER_V &&
                tile != H_RAILROAD &&
                tile != H_RAIL);
    }

    public static boolean railConnectsSouth(int tile) {
        tile = neutralizeRoad(tile);
        return (tile >= RAIL_H_POWER_V && tile <= V_RAILROAD &&
                tile != RAIL_H_POWER_V &&
                tile != H_RAILROAD &&
                tile != H_RAIL);
    }

    public static boolean railConnectsWest(int tile) {
        tile = neutralizeRoad(tile);
        return (tile >= RAIL_H_POWER_V && tile <= V_RAILROAD &&
                tile != RAIL_V_POWER_H &&
                tile != V_RAILROAD &&
                tile != V_RAIL);
    }

    public static boolean isWireDynamic(int tile) {
        assert (tile & LOW_MASK) == tile;

        return (tile >= LH_POWER && tile <= LV_POWER_10);
    }

    public static boolean wireConnectsEast(int tile) {
        int ntile = neutralizeRoad(tile);
        return (isConductive(tile) &&
                ntile != H_POWER &&
                ntile != H_ROAD_POWER &&
                ntile != RAIL_H_POWER_V);
    }

    public static boolean wireConnectsNorth(int tile) {
        int ntile = neutralizeRoad(tile);
        return (isConductive(tile) &&
                ntile != V_POWER &&
                ntile != V_ROAD_POWER &&
                ntile != RAIL_V_POWER_H);
    }

    public static boolean wireConnectsSouth(int tile) {
        int ntile = neutralizeRoad(tile);
        return (isConductive(tile) &&
                ntile != V_POWER &&
                ntile != V_ROAD_POWER &&
                ntile != RAIL_V_POWER_H);
    }

    public static boolean wireConnectsWest(int tile) {
        int ntile = neutralizeRoad(tile);
        return (isConductive(tile) &&
                ntile != H_POWER &&
                ntile != H_ROAD_POWER &&
                ntile != RAIL_H_POWER_V);
    }

    public static boolean isCommercialZone(int tile) {
        assert (tile & LOW_MASK) == tile;

        TileSpec ts = Tiles.get(tile);
        if (ts != null) {
            if (ts.owner != null) {
                ts = ts.owner;
            }
            return ts.getBooleanAttribute("commercial-zone");
        }
        return false;
    }

    public static boolean isHospitalOrChurch(int tile) {
        assert (tile & LOW_MASK) == tile;

        return tile >= HOSPITAL &&
                tile < COMMERCIAL_BASE;
    }

    /**
     * Checks whether the tile is defined with the "industrial-zone" attribute.
     * Note: the old version of this function erroneously included the coal power
     * plant smoke as an industrial zone.
     */
    public static boolean isIndustrialZone(int tile) {
        assert (tile & LOW_MASK) == tile;

        TileSpec ts = Tiles.get(tile);
        if (ts != null) {
            if (ts.owner != null) {
                ts = ts.owner;
            }
            return ts.getBooleanAttribute("industrial-zone");
        }
        return false;
    }

    public static boolean isResidentialClear(int tile) {
        assert (tile & LOW_MASK) == tile;

        return tile >= RESIDENTIAL_BASE && tile <= RESIDENTIAL_BASE + 8;
    }

    /**
     * Note: does not include hospital/church.
     *
     * @see #isHospitalOrChurch
     */
    public static boolean isResidentialZone(int tile) {
        assert (tile & LOW_MASK) == tile;

        return tile >= RESIDENTIAL_BASE &&
                tile < HOSPITAL;
    }

    // includes hospital/church.
    public static boolean isResidentialZoneAny(int tile) {
        assert (tile & LOW_MASK) == tile;

        TileSpec ts = Tiles.get(tile);
        if (ts != null) {
            if (ts.owner != null) {
                ts = ts.owner;
            }
            return ts.getBooleanAttribute("residential-zone");
        }
        return false;
    }

    /**
     * Tile represents a part of any sort of building.
     */
    public static boolean isZoneAny(int tile) {
        assert (tile & LOW_MASK) == tile;

        return tile >= RESIDENTIAL_BASE;
    }

    public static boolean isZoneCenter(int tile) {
        assert (tile & LOW_MASK) == tile;

        TileSpec spec = Tiles.get(tile);
        return spec != null && spec.zone;
    }

    /**
     * Converts a road tile value with traffic to the equivalent
     * road tile without traffic.
     */
    public static char neutralizeRoad(int tile) {
        assert (tile & LOW_MASK) == tile;

        if (tile >= ROAD_BASE && tile <= LAST_ROAD) {
            tile = ((tile - ROAD_BASE) & 0xf) + ROAD_BASE;
        }
        return (char) tile;
    }

    /**
     * Determine the population level of a Residential zone
     * tile. Note: the input tile MUST be a full-size res zone,
     * it cannot be an empty zone.
     *
     * @return int multiple of 8 between 16 and 40.
     */
    public static int residentialZonePop(int tile) {
        assert (tile & LOW_MASK) == tile;

        TileSpec ts = Tiles.get(tile);
        return ts.getPopulation();
    }

    /**
     * Determine the population level of a Commercial zone
     * tile.
     * The input tile MAY be an empty zone.
     *
     * @return int between 0 and 5.
     */
    public static int commercialZonePop(int tile) {
        assert (tile & LOW_MASK) == tile;

        TileSpec ts = Tiles.get(tile);
        return ts.getPopulation() / 8;
    }

    /**
     * Determine the population level of an Industrial zone tile.
     * The input tile MAY be an empty zone.
     *
     * @return int between 0 and 4.
     */
    public static int industrialZonePop(int tile) {
        assert (tile & LOW_MASK) == tile;

        TileSpec ts = Tiles.get(tile);
        return ts.getPopulation() / 8;
    }
}
