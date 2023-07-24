package com.raspberry.movieinfo.controller;

import com.raspberry.movieinfo.entity.Movie;
import com.raspberry.movieinfo.service.MoviewService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class MovieController {
    private ModelAndView mv;

    @Autowired
    private MoviewService mServ;

    @GetMapping("/")
    public ModelAndView home(Integer pageNum, HttpSession session){
        log.info("home()");
        mv = mServ.getMovieList(pageNum, session); //pageNum, session을 받아 메서드 호출
        return mv;
    }

    //영화 등록화면 이동
    @GetMapping("writeForm")
    public String writeForm(){
        log.info("writeForm()");
        return "writeForm"; //writeForm화면으로 이동
    }

    //영화 등록 프로세스
    @PostMapping("writeProc")
    public String writeProc(@RequestPart List<MultipartFile> files, Movie movie,
                            HttpSession session, RedirectAttributes rttr){
        log.info("writeProc()");
        String view = mServ.insertMovie(files,movie,session,rttr);
        return view;
    }

    //영화 세부정보화면 이동
    @GetMapping("detail")
    public ModelAndView detail(Long mcode){
        log.info("detail()");
        mv = mServ.getMovie(mcode);
        return mv;
    }

    //영화 정보 수정 화면 이동
    @GetMapping("updateForm")
    public ModelAndView updateForm(Long mcode){
        log.info("updateForm()");
        mv = mServ.getMovie(mcode);
        mv.setViewName("updateForm");
        return mv;
    }

    //영화 정보 수정 프로세스
    @PostMapping("updateProc")
    public String updateProc(@RequestPart List<MultipartFile> files, Movie movie,
                             HttpSession session, RedirectAttributes rttr){
        log.info("updateProc()");
        String view = mServ.updateMovie(files,movie,session,rttr);
        return view;
    }

    //영화 삭제 프로세스
    @GetMapping("delete")
    public String deleteProc(Long mcode, String msysname, HttpSession session, RedirectAttributes rttr){
        log.info("deleteProc()");
        String view = mServ.deleteMovie(mcode,msysname,session,rttr);
        return view;
    }

}
