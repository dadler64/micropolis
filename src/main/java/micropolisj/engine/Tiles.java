// This file is part of MicropolisJ.
// Copyright (C) 2013 Jason Long
// Portions Copyright (C) 1989-2007 Electronic Arts Inc.
//
// MicropolisJ is free software; you can redistribute it and/or modify
// it under the terms of the GNU GPLv3, with additional terms.
// See the README file, included in this distribution, for details.

package micropolisj.engine;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Provides global methods for loading tile specifications.
 */
public class Tiles {

    static final Charset UTF8 = StandardCharsets.UTF_8;
    static TileSpec[] tiles;
    static Map<String, TileSpec> tilesByName = new HashMap<>();

    static {
        try {
            readTiles();
            checkTiles();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void readTiles() throws IOException {
        ArrayList<TileSpec> tilesList = new ArrayList<>();

        Properties tilesRc = new Properties();
        tilesRc.load(new InputStreamReader(Tiles.class.getResourceAsStream("/graphics/tiles.rc"), UTF8));

        String[] tileNames = TileSpec.generateTileNames(tilesRc);
        tiles = new TileSpec[tileNames.length];

        for (int i = 0; i < tileNames.length; i++) {
            String tileName = tileNames[i];
            String rawSpec = tilesRc.getProperty(tileName);
            if (rawSpec == null) {
                break;
            }

            TileSpec tileSpec = TileSpec.parse(i, tileName, rawSpec, tilesRc);
            tilesByName.put(tileName, tileSpec);
            tiles[i] = tileSpec;
        }

	    for (TileSpec tile : tiles) {
		    tile.resolveReferences(tilesByName);

		    TileSpec.BuildingInfo buildingInfo = tile.getBuildingInfo();
		    if (buildingInfo != null) {
			    for (int j = 0; j < buildingInfo.members.length; j++) {
				    int tid = buildingInfo.members[j];
				    int xOffset = (buildingInfo.width >= 3 ? -1 : 0) + j % buildingInfo.width;
				    int yOffset = (buildingInfo.height >= 3 ? -1 : 0) + j / buildingInfo.width;

				    if (tiles[tid].owner == null && (xOffset != 0 || yOffset != 0)) {
					    tiles[tid].owner = tile;
					    tiles[tid].ownerOffsetX = xOffset;
					    tiles[tid].ownerOffsetY = yOffset;
				    }
			    }
		    }
	    }
    }

    public static TileSpec load(String tileName) {
        return tilesByName.get(tileName);
    }

    /**
     * Access a tile specification by index number.
     *
     * @return a tile specification, or null if there is no tile
     * with the given number
     */
    public static TileSpec get(int tileNumber) {
        if (tileNumber >= 0 && tileNumber < tiles.length) {
            return tiles[tileNumber];
        } else {
            return null;
        }
    }

    static void checkTiles() {
        for (int i = 0; i < tiles.length; i++) {
            // do something here
        }
    }
}
