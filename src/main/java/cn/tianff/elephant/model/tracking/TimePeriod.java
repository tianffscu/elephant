package cn.tianff.elephant.model.tracking;

public enum TimePeriod {

    /**
     * Six period of one day
     * TODO: 暂时不做时间段自定义，时间段划分全部走以下六个子段
     */
    Midnight2SixAM,
    SixAM2NineAM,
    NineAM2Noon,
    Noon2TwoPM,
    TwoPM2SixPM,
    SixPM2MidNight;
}
