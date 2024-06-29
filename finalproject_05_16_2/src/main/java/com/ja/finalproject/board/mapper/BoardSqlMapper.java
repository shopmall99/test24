package com.ja.finalproject.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ja.finalproject.dto.ArticleDto;

@Mapper
public interface BoardSqlMapper {
    public void createArticle(ArticleDto articleDto);
    public List<ArticleDto> findArticleAll();
    public ArticleDto findArticleById(int id);

    public void increaseReadCount(int id);

    public void deleteArticleById(int id);
    public void updateArticle(ArticleDto articleDto);
}
