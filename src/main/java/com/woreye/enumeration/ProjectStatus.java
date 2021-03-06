package com.woreye.enumeration;

public enum ProjectStatus {
	COMMON("普通项目", 1),RECOMMEND("推荐项目", 2);
	private final int status;
	private final String name;
	
	ProjectStatus(String name, int status){
		this.status = status;
		this.name = name;
	}
	
	public static String getNameByValue(int status){
		for(ProjectStatus projectStatus : ProjectStatus.values()){
			if(projectStatus.getStatus() == status)
				return projectStatus.getName();
		}
		return "";
	}
	
	public Byte getStatus(){
		return Byte.valueOf(status+"");
	}
	
	public String getName(){
		return name;
	}
}
