package com.hby.pojo;

public class Orderitem extends OrderitemKey {
    private Integer orderNum;
    
    private Article article;

    public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}