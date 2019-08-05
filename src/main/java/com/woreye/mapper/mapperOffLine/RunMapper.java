package com.woreye.mapper.mapperOffLine;

import com.woreye.pojo.Run;

public interface RunMapper {
    public void saveTestRun(Run r);
    public int getTestRunNumByDate(String date);
    public int getTestPassNumByDate(String date);
    public int getTestFailNumByDate(String date);
    public int getCountRun();
    public int getPassRun();
    public int getFailRun();


}
