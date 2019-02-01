package com.fuhua.wx.pojo.menu;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ClickButton extends Button {
    private String key;
}
