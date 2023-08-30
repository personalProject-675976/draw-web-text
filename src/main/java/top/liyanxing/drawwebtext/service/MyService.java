package top.liyanxing.drawwebtext.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import top.liyanxing.common.CommonResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyService
{
    private static final List<String> TAG_NAME_LIST = CollUtil.newArrayList("h1", "h2", "h3", "h4", "h5", "h6", "p", "div");

    /**
     * 提取网页上的文字
     * @param url 网页url
     * @return 文字
     */
    public CommonResult<String> getText(String url)
    {
        Assert.notBlank(url, "url不能为空");
        List<Element> eleOfHPD = this.getEleOfHPD(url);
        String htmlText = eleOfHPD.stream().map(Element::toString).collect(Collectors.joining());

        return CommonResult.successData(htmlText);
    }

    /**
     * 获得所有的最外层 hx p div 标签
     * @param url url
     * @return elList
     */
    public List<Element> getEleOfHPD(String url)
    {
        Document document;
        try
        {
            document = Jsoup.parse(URLUtil.url(url), 5000);
        }
        catch (Exception e)
        {
            throw new RuntimeException(StrUtil.format("解析url出错：{}", e.getMessage()));
        }

        List<Element> eleList = CollUtil.newArrayList();
        Elements allElement = document.getAllElements();
        for (Element el : allElement)
        {
            if (TAG_NAME_LIST.contains(el.tagName()))
            {
                List<String> parentNameList = el.parents().stream().map(Element::tagName).collect(Collectors.toList());
                if (!CollUtil.containsAny(parentNameList, TAG_NAME_LIST))
                {
                    eleList.add(el);
                }
            }
        }

        return eleList;
    }
}
