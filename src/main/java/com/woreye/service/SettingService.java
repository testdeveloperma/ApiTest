package com.woreye.service;

import com.woreye.config.Config;
import com.woreye.mapper.mapperOffLine.SettingMapper;
import com.woreye.pojo.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


@Service
public class SettingService {

	@Autowired
	private SettingMapper settingMapper;
	@Autowired
	Config config;

	/**
	 *
	 * @param
	 * @return
	 */
	public List<Setting> getSeetings(){
		return settingMapper.getSettings();
	}

	public List<Setting> getSettingByMap(Map m){
		return settingMapper.getSettingByMap(m);
	}
}
