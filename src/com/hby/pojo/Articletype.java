package com.hby.pojo;

import java.util.List;

public class Articletype {
    private String code;

    private String name;

    private String remark;
    
    List<SecondArticleType> secondArticleType;

    public List<SecondArticleType> getSecondArticleType() {
		return secondArticleType;
	}

	public void setSecondArticleType(List<SecondArticleType> secondArticleType) {
		this.secondArticleType = secondArticleType;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}