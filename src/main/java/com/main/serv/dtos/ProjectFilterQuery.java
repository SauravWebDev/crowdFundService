package com.main.serv.dtos;

public class ProjectFilterQuery {
    public String getRankingBy() {
        return rankingBy;
    }

    public void setRankingBy(String rankingBy) {
        this.rankingBy = rankingBy;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    private String ownerId;
    private String rankingBy;
}
