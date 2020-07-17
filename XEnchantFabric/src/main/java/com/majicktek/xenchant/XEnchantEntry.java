package com.majicktek.xenchant;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class XEnchantEntry implements ModInitializer{
    private static Logger logger = LogManager.getLogger("xenchant");
    @Override
    public void onInitialize() {
        logger.info("XEnchant has been initialized!");
    }

    public static void log(String message) {
        logger.info("[XEnchant]: " + message);
    }


}
