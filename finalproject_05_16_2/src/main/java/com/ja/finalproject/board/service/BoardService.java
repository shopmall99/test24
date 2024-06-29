package com.ja.finalproject.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.finalproject.board.mapper.BoardSqlMapper;
import com.ja.finalproject.dto.ArticleDto;
import com.ja.finalproject.dto.UserDto;
import com.ja.finalproject.user.mapper.UserSqlMapper;

@Service
public class BoardService {

    @Autowired
    private BoardSqlMapper boardSqlMapper;
    @Autowired
    private UserSqlMapper userSqlMapper;

    public void registerArticle(ArticleDto articleDto) {
        boardSqlMapper.createArticle(articleDto);
    }

    public List<Map<String, Object>> getArticleList() {
        List<Map<String, Object>> result = new ArrayList<>();
        List<ArticleDto> articleDtoList = boardSqlMapper.findArticleAll();

        for(ArticleDto articleDto : articleDtoList) {
            int writerPk = articleDto.getUser_id();
            UserDto userDto = userSqlMapper.findUserById(writerPk);

            Map<String, Object> map = new HashMap<>();
            map.put("articleDto", articleDto);
            map.put("userDto", userDto);

            result.add(map);
        }

        return result;
    }

    public Map<String, Object> getArticle(int id) {
        Map<String, Object> map = new HashMap<>();

        ArticleDto articleDto = boardSqlMapper.findArticleById(id);
        UserDto userDto = userSqlMapper.findUserById(articleDto.getUser_id());

        map.put("articleDto", articleDto);
        map.put("userDto", userDto);

        return map;
    }

    public void increaseReadCount(int id) {
        boardSqlMapper.increaseReadCount(id);
    }

    public void deleteArticle(int id) {
        boardSqlMapper.deleteArticleById(id);
    }

    public void updateArticle(ArticleDto articleDto) {
        boardSqlMapper.updateArticle(articleDto);
    }


}
