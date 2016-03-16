package com.tw.bill;

public class RuleInfo {
	
	/**
	 * 会员卡信息
	 */
	private ClubCard clubCard;

	public ClubCard getClubCard() {
		return clubCard;
	}
	public void setClubCard(ClubCard clubCard) {
		this.clubCard = clubCard;
	}
	private String ruleName;
	private String jarFileName;
	private String jarFilePath;
	private String className;
	private Integer level = 99;
	private String[] RULS = {};
	/**
	 * 加载规则位置
	 * @param jarFileName	jar文件名称（Ruls_Buy3Free1And95off.jar）
	 * @param jarFilePath	相对路径（存放jar的文件夹 rules）
	 * @param className		规则实现类的名字（com.tw.bill.rule.Ruls_Buy3Free1）
	 * @param level			优先级，整数，越小越靠前
	 * @param rULS			规则范围（商品ID的数组）
	 */
	public RuleInfo(String jarFileName, String jarFilePath, String className,
			Integer level, String[] rULS) {
		super();
		this.jarFileName = jarFileName;
		this.jarFilePath = jarFilePath;
		this.className = className;
		this.level = level;
		RULS = rULS;
	}
	public RuleInfo(){}
	public String getRuleFullPathWithJarFileName(){
		return this.jarFilePath+"\\"+this.jarFileName;
	}
	public void setJarFileName(String jarFileName) {
		this.jarFileName = jarFileName;
	}
	public String getJarFileName() {
		return jarFileName;
	}
	public String getJarFilePath() {
		return jarFilePath;
	}
	public void setJarFilePath(String jarFilePath) {
		this.jarFilePath = jarFilePath;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public int compareTo(RuleInfo arg0) {
		//按照优先级排序
        return this.getLevel().compareTo(arg0.getLevel());
    }
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getLevel() {
		return level;
	}
	public void setRULS(String[] rULS) {
		RULS = rULS;
	}
	public String[] getRULS() {
		return RULS;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getRuleName() {
		return ruleName;
	}
}
