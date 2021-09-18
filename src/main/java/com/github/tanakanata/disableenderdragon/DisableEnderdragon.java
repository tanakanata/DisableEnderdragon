package com.github.tanakanata.disableenderdragon;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class DisableEnderdragon extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("有効化しました。");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event){
        //ブロック設置のイベントか判定
        if (!event.hasBlock()){
            return;
        }
        //イベントが発生したディメンションがエンドか判定
        if (event.getPlayer().getWorld().getEnvironment() != World.Environment.THE_END){
            return;
        }
        //使用したアイテムがエンドクリスタルか判定
        if (event.getMaterial() != Material.END_CRYSTAL ) {
            return;
        }
        //nullチェック
        if (event.getClickedBlock() == null){
            return;
        }
        //クリックしたブロックが岩盤かどうか判定
        if (event.getClickedBlock().getType() != Material.BEDROCK){
            return;
        }
        //設置イベントをキャンセル
        event.setCancelled(true);
        event.getPlayer().sendMessage("§Cこのワールドでは再召喚できません。");
    }

}
