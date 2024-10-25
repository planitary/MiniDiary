package com.planitary.base.commonEnum;

import com.planitary.core.exception.MDException;
import lombok.Getter;

import java.util.Objects;

/**
 * @author zane
 * @date 2024-10-25 16:27:02
 */
@Getter
public enum AppEnum {
    ALIPAY("支付宝","AliPay"),
    WECHAT("微信","WeChat"),
    TaoBao("淘宝","TaoBao"),
    TMall("天猫","TMall"),
    BDNetDisk("百度网盘","BDDisk"),
    NECMusic("网易云音乐","NetEase"),
    SPOTIFY("Spotify","Spotify"),
    GAME("游戏","Game"),
    CHARGING("充值","Charge");


    private final String bizType;
    private final String bizCode;

    AppEnum(String bizType,String bizCode){
        this.bizType = bizType;
        this.bizCode = bizCode;
    }

    public static AppEnum getTypeByCode(String bizCode){
        for (AppEnum appEnum : AppEnum.values()){
            if (Objects.equals(appEnum.getBizCode(), bizCode)){
                return appEnum;
            }
        }
        // 枚举不存在
        throw new MDException(ExceptionEnum.ENUM_NOT_EXIST.getErrMessage());
    }
}
