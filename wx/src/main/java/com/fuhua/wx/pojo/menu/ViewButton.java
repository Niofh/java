package com.fuhua.wx.pojo.menu;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViewButton extends Button {
    private String url;
}
