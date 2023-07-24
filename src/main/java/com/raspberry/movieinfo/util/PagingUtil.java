package com.raspberry.movieinfo.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PagingUtil {
    private int totalPage; //전체 페이지 개수
    private int pageNum; //현재 보이는 페이지 번호
    private int pageCnt; //페이지당 보여질 번호 개수
    private String urlStr; //링크 url

    public String makePaging(){
        String pageHtml = null;
        StringBuffer sb = new StringBuffer(); //문자열 변경할수있는 가변 클래스

        //현재 그룹
        //페이지 수 계산
        int curGroup = (pageNum % pageCnt) > 0 ?
                        pageNum/pageCnt + 1 :
                        pageNum/pageCnt;
        //만약 pageNum을 pageCnt로 나눈 나머지가 0보다 크다면,
        //나눈 몫에 1을 더함 그렇지않으면 나눈 몫을 반환하여 페이지 수 계산


        //그룹의 시작번호(첫페이지 번호 계산)
        int start = (curGroup * pageCnt) - (pageCnt - 1);
        //그룹의 끝번호(마지막 페이지 번호 계산)
        int end = (curGroup * pageCnt) >= totalPage ?
                totalPage : curGroup * pageCnt;

        //page html작성
        if (start != 1){
            sb.append("<a class='pno' href='/" + urlStr + "pageNum=" + (start - 1) + "'>◀</a>");
        }

        for (int i = start; i <= end; i++){
            if (pageNum == i){
                sb.append("<font class='pno'>" + i + "</font>");
            }
            else {
                sb.append("<a class='pno' href='/" + urlStr + "pageNum=" + i + "'>" + i + "</a>");
            }
        }
        if (end != totalPage){
            sb.append("<a class='pno' href='/" + urlStr + "pageNum=" + (end + 1) + "'>▶</a>");
        }//<a class='pno' href='/?pageNum=6>▶</a>

        //StringBuffer를 String으로 변환
        pageHtml = sb.toString();

        return pageHtml;
    }
}//class end
