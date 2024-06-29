package com.ja.finalproject.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.finalproject.board.service.BoardService;
import com.ja.finalproject.dto.ArticleDto;
import com.ja.finalproject.dto.UserDto;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @RequestMapping("mainPage")
    public String mainPage(Model model) {
        // 여기서 할거 짱 많음

        List<Map<String, Object>> boardDataList = boardService.getArticleList();
        
        model.addAttribute("boardDataList", boardDataList);

        return "board/mainPage";
    }

    @RequestMapping("writeArticlePage")
    public String writeArticlePage() {

        return "board/writeArticlePage";
    }

    @RequestMapping("writeArticleProcess")
    public String writeArticleProcess(HttpSession session, ArticleDto params) {

        UserDto sessionUserInfo = (UserDto)session.getAttribute("sessionUserInfo");
        int userPk = sessionUserInfo.getId();

        params.setUser_id(userPk);

        boardService.registerArticle(params);

        return "redirect:./mainPage";
    }

    @RequestMapping("readArticlePage")
    public String readArticlePage(Model model, int id) {

        boardService.increaseReadCount(id);

        Map<String, Object> boardData = boardService.getArticle(id);
        model.addAttribute("boardData", boardData);

        return "board/readArticlePage";
    }

    @RequestMapping("deleteArticleProcess")
    public String deleteArticleProcess(int id) {
        boardService.deleteArticle(id);
        return "redirect:./mainPage";
    }

    @RequestMapping("updateArticlePage")
    public String updateArticlePage(Model model, int id) {

        Map<String, Object> boardData = boardService.getArticle(id);
        model.addAttribute("boardData", boardData);

        return "board/updateArticlePage";
    }

    @RequestMapping("updateArticleProcess")
    public String updateArticleProcess(ArticleDto params) {

        boardService.updateArticle(params);

        return "redirect:./mainPage";
    }


}
