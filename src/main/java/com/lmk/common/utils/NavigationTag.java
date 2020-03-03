package com.lmk.common.utils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Map;

public class NavigationTag extends TagSupport {
    static final long serialVersionUID= 2372405317744358833L;
    private String bean="page";
    private String url=null;
    private int num=5;

    @Override
    public int doStartTag() throws JspException {
        JspWriter jspWriter=pageContext.getOut();
        HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
        Page page=(Page)request.getAttribute(bean);
        if(page==null)
            return SKIP_BODY;
        url=resolveUrl(url,pageContext);
        try {
            //计算总数
            int pageCount=page.getTotal()/page.getSize();
            if(page.getTotal()%page.getSize()>0){
                pageCount++;
            }
            jspWriter.print("<nav><ul class=\"pagination\">");
            String homeUrl=append(url,"page",1);
            String backUrl=append(url,"page",pageCount);
            if (page.getPage()>1){
                String preUrl=append(url,"page",page.getPage()-1);
                preUrl=append(url,"rows",page.getSize());
                jspWriter.print("<li><a href\""+homeUrl+"\">"+"首页</a></li>");
                jspWriter.print("<li><a href=\""+preUrl+"\">"+"上一页</a></li>");
            }else{
                jspWriter.print("<li class=\"disabled\"><a href=\"#\">"+"首页</a></li>");
                jspWriter.print("<li calss=\"disable\"><a href=\"#\">"+"上一页</a></li>");
            }
            int indxePage=1;
            if (page.getPage()-2<=0){
                indxePage=1;
            }else if(pageCount- page.getPage()<=2){
                indxePage=pageCount-4;
            }else{
                indxePage=page.getPage()-2;
            }
            for (int i=1;i<=num&&indxePage<=pageCount;indxePage++,i++){
                if(indxePage==page.getPage()){
                    jspWriter.print("<li calss=\"active\"><a href=\"#\">"+indxePage+"<span class=\"sr-only\">(current)</span></a></li>");
                    continue;
                }
                String pageUrl=append(url,"page",indxePage);
                pageUrl=append(pageUrl,"rows",page.getSize());
                jspWriter.print("<li><a href=\""+pageUrl+"\">"+indxePage+"</a></li>" );
            }
            if(page.getPage()<pageCount){
                String nextUrl=append(url,"page",page.getPage()+1);
                nextUrl=append(nextUrl,"rows",page.getPage());
                jspWriter.print("<li><a href=\""+nextUrl+"\">"+"下一页</a></li>");
                jspWriter.print("<li><a href=\""+backUrl+"\">"+"尾页</a></li>");
            }else{
                jspWriter.print("<li class=\"disable\"><a href=\"#\">"+"下一页</a></li>");
                jspWriter.print("<li class=\"disable\"><a href=\"#\">"+"尾页</a></li>");
            }
            jspWriter.print("</nav>");
        }catch (IOException e){
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
    private String append(String url,String key,int value){
        return append(url, key, String.valueOf(value));
    }
    //为url参加参数对
    private String append(String url,String key,String value){
        if(url==null||url.trim().length()==0){
            return "";
        }
        if(url.indexOf("?")==-1){
            url=url+"?"+key+"="+value;
        }else {
            if(url.endsWith("?")){
                url=url+"&amp;"+key+"="+value;
            }
        }
        return url;
    }
    /*为url添加翻页请求参数*/
    private String resolveUrl(String url,javax.servlet.jsp.PageContext pageContext) throws JspException{
        Map params=pageContext.getRequest().getParameterMap();
        for(Object key:params.keySet()){
            if("page".equals(key)||"rows".equals(key)){
                continue;
            }
            Object value=params.get(key);
            if(value==null){
                continue;
            }
            if(value.getClass().isArray()){
                url=append(url,key.toString(),((String[]) value)[0]);
            }else if(value instanceof String){
                url=append(url,key.toString(),value.toString());
            }
        }
        return url;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
