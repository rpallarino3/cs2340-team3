package etc;

import java.util.ArrayList;

import model.*;

import com.google.gson.*;

/**
 * This class contains utilities function (static) that will help parsing model objects into JSON objects/elements to be send back to client.
 * @author Khanh
 */
public class AjaxSupport {

    public static JsonArray makePlayersJsonArray(ArrayList<Player> players) {
        JsonArray playersJson = new JsonArray();
        for(Player player : players) {
            playersJson.add(makePlayerJsonObject(player));
        }
        return playersJson;
    }

    public static JsonObject makePlayerJsonObject(Player player) {
        JsonObject playerJson = new JsonObject();
        playerJson.addProperty("name",player.getName());
        playerJson.addProperty("color",player.getColor());
        playerJson.addProperty("armies",player.getArmiesAvailable());
        return playerJson;
    }

    public static JsonArray makeTerritoriesJsonArray(ArrayList<Territory> territories) {
        JsonArray territoriesJson = new JsonArray();
        for(Territory territory : territories) {
            territoriesJson.add(makeTerritoryJsonObject(territory));
        }
        return territoriesJson;
    }

    public static JsonObject makeTerritoryJsonObject(Territory territory) {
        JsonObject territoryJson = new JsonObject();
        territoryJson.addProperty("name",territory.getName());
        territoryJson.addProperty("color",(territory.getPlayerOwned()==null ? Territory.DEF_TERRITORY_COLOR : territory.getPlayerOwned().getColor()));
        territoryJson.addProperty("owner",territory.getPlayerOwned().getName());
        territoryJson.addProperty("armies",territory.getNumArmies());
        territoryJson.addProperty("offsetX",territory.getMarginLeft());
        territoryJson.addProperty("offsetY",territory.getMarginTop());
        return territoryJson;
    }

    public static JsonObject makeCurrentStatJsonObject(Game game) {
        return makeCurrentStatJsonObject(game, game.getConsole().getRecentRaws());
    }

    public static JsonObject makeCurrentStatJsonObject(Game game, String messageOut) {
        JsonObject statJson = new JsonObject();
        statJson.addProperty("stage",game.getStage());
        statJson.addProperty("player",game.getCurrentPlayer().getName());
        statJson.addProperty("messages",messageOut);
        return statJson;
    }

    public static JsonObject makeReplyJsonPackage(JsonArray players, JsonArray territories, JsonObject currentStat) {
        JsonObject riskJson = new JsonObject();
        riskJson.add("players",players);
        riskJson.add("territories",territories);
        riskJson.add("current",currentStat);
        return riskJson;
    }

    public static JsonObject makeReplyJsonPackage(ArrayList<Player> players, ArrayList<Territory> territories, Game game) {
        return makeReplyJsonPackage(makePlayersJsonArray(players), makeTerritoriesJsonArray(territories), makeCurrentStatJsonObject(game));
    }
}
