package ru.kelcuprum.simplystatus.mods;

import com.replaymod.replay.ReplayHandler;
import com.replaymod.replay.ReplayModReplay;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.simplystatus.SimplyStatus;

public class ReplayMod {
    public String name = "";
    public String address = "";
    public long date = 0;
    /**
     * ReplayMod support
     */
    public ReplayMod() {
        try {
            ReplayHandler handler = ReplayModReplay.instance.getReplayHandler();
            name = handler.getReplayFile().getMetaData().getCustomServerName();
            address = handler.getReplayFile().getMetaData().getServerName();
            date = handler.getReplayFile().getMetaData().getDate();
        } catch(Exception e){
            SimplyStatus.log(e.getLocalizedMessage(), Level.ERROR);
        }
    }
}
