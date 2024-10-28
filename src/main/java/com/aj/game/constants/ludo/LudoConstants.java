package com.aj.game.constants.ludo;

import java.util.Arrays;
import java.util.List;

public class LudoConstants {
    public static final String STANDARD_POSITION = "STANDARD_POSITION";
    public static final String SAFE_ONLY = "SAFE_ONLY";
    public static final String SAFE_N_HOME = "SAFE_N_HOME";
    public static final String SAFE_N_ENTRY = "SAFE_N_ENTRY";

    public static final List<String> SAFE_POSITIONS = Arrays.asList(SAFE_N_HOME, SAFE_ONLY, SAFE_N_ENTRY);
}
