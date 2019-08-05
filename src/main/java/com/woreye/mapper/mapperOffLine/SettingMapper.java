package com.woreye.mapper.mapperOffLine;

import com.woreye.pojo.Setting;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 16-12-14.
 */
public interface SettingMapper {

    /**
     * 查询设置
     * @return
     */
    public List<Setting> getSettings();

    public List<Setting> getSettingByMap(Map m);
}
