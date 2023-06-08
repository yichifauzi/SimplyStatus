package ru.simplykel.simplystatus.config;

import net.minecraft.client.MinecraftClient;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AssetsConfig {
    /**
     * Начальная иконка
     */
    public String logo = "";

    /**
     * День
     */
    public String day = "";
    /**
     * Ночь
     */
    public String night = "";
    /**
     * Не ебу
     */
    public String morning = "";
    /**
     * Не ебу 2
     */
    public String evening = "";

    /**
     * Верхний мир
     */
    public String world = "";
    /**
     * Нижний мир
     */
    public String world_nether = "";
    /**
     * The End
     */
    public String world_the_end = "";
    /**
     * The End
     */
    public String world_moon = "";

    /**
     * Music
     */
    public String music = "";
    /**
     * ReplayMod
     */
    public String replaymod = "";
    /**
     * PlasmoVoice or Simple Voice Chat
     */
    public String voice = "";
    /**
     * Неизвестный мир
     */
    public String unknown_world = "";
    /**
     * Неизвестное значение
     */
    public String unknown = "";
    public void loadUserAssets(){
        MinecraftClient mc = MinecraftClient.getInstance();
        final Path assetsFile = mc.runDirectory.toPath().resolve("SimplyStatus/assets.json");
        try {
            JSONObject jsonConfig = new JSONObject();
            if(assetsFile.toFile().exists()) jsonConfig = new JSONObject(Files.readString(assetsFile));
            AssetsConfig assets = new AssetsConfig(jsonConfig, false);
            logo = assets.logo;
            day = assets.day;
            night = assets.night;
            morning = assets.morning;
            evening = assets.evening;

            world = assets.world;
            world_nether = assets.world_nether;
            world_the_end = assets.world_the_end;

            replaymod = assets.replaymod;
            voice = assets.voice;
            music = assets.music;

            unknown_world = assets.unknown_world;
            unknown = assets.unknown;

        } catch (Exception e){
            e.printStackTrace();
            saveUserAssets();
        }
    }
    public void saveUserAssets(){
        MinecraftClient mc = MinecraftClient.getInstance();
        final Path assetsFile = mc.runDirectory.toPath().resolve("SimplyStatus/assets.json");
        JSONObject jsonConfig = new JSONObject();
        jsonConfig.put("logo", logo)
                .put("day", day)
                .put("night", night)
                .put("morning", morning)
                .put("evening", evening)
                .put("world", world)
                .put("world_nether", world_nether)
                .put("world_the_end", world_the_end)
                .put("replaymod", replaymod)
                .put("music", music)
                .put("voice", voice)
                .put("unknown", unknown)
                .put("unknown_world", unknown_world);
        try {
            Files.createDirectories(assetsFile.getParent());
            Files.writeString(assetsFile, jsonConfig.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public AssetsConfig(){
        AssetsConfig assets = new AssetsConfig(ModConfig.assets.getJSONObject(UserConfig.USE_ASSETS.toLowerCase()), false);
            logo = assets.logo;
            day = assets.day;
            night = assets.night;
            morning = assets.morning;
            evening = assets.evening;

            world = assets.world;
            world_nether = assets.world_nether;
            world_the_end = assets.world_the_end;
            world_moon = assets.world_moon;

            replaymod = assets.replaymod;
            voice = assets.voice;
            music = assets.music;

            unknown_world = assets.unknown_world;
            unknown = assets.unknown;
    }
    public AssetsConfig(JSONObject jsonAssets, boolean isLoadingResources){
        AssetsConfig assets = ModConfig.defaultAssets;
        if(jsonAssets.isNull("logo")) logo = nullAsset(isLoadingResources, assets.logo);
        else logo = jsonAssets.getString("logo");

        if(jsonAssets.isNull("day")) day = nullAsset(isLoadingResources, assets.day);
        else day = jsonAssets.getString("day");
        if(jsonAssets.isNull("night")) night = nullAsset(isLoadingResources, assets.night);
        else night = jsonAssets.getString("night");
        if(jsonAssets.isNull("morning")) morning = nullAsset(isLoadingResources, assets.morning);
        else morning = jsonAssets.getString("morning");
        if(jsonAssets.isNull("evening")) day = nullAsset(isLoadingResources, assets.evening);
        else evening = jsonAssets.getString("evening");

        if(jsonAssets.isNull("world")) world = nullAsset(isLoadingResources, assets.world);
        else world = jsonAssets.getString("world");
        if(jsonAssets.isNull("world_nether")) world_nether = nullAsset(isLoadingResources, assets.world_nether);
        else world_nether = jsonAssets.getString("world_nether");
        if(jsonAssets.isNull("world_the_end")) world_the_end = nullAsset(isLoadingResources, assets.world_the_end);
        else world_the_end = jsonAssets.getString("world_the_end");
        if(jsonAssets.isNull("world_moon")) world_moon = nullAsset(isLoadingResources, assets.world_moon);
        else world_moon = jsonAssets.getString("world_moon");

        if(jsonAssets.isNull("replaymod")) replaymod = nullAsset(isLoadingResources, assets.replaymod);
        else replaymod = jsonAssets.getString("replaymod");
        if(jsonAssets.isNull("music")) music = nullAsset(isLoadingResources, assets.music);
        else music = jsonAssets.getString("music");
        if(jsonAssets.isNull("voice")) voice = nullAsset(isLoadingResources, assets.voice);
        else voice = jsonAssets.getString("voice");

        if(jsonAssets.isNull("unknown_world")) unknown_world = nullAsset(isLoadingResources, assets.unknown_world);
        else unknown_world = jsonAssets.getString("unknown_world");
        if(jsonAssets.isNull("unknown")) unknown = nullAsset(isLoadingResources, assets.unknown);
        else unknown = jsonAssets.getString("unknown");
    }
    private String nullAsset(boolean isResourcesLoading, String type){
        if(isResourcesLoading){
            return "https://assets.simplykel.ru/monke.gif";
        } else {
            return type;
        }
    }
}
