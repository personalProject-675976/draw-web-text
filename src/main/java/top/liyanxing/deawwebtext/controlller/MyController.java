package top.liyanxing.deawwebtext.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.liyanxing.common.CommonResult;
import top.liyanxing.deawwebtext.service.MyService;

@RestController
public class MyController
{
    @Autowired
    private MyService myService;

    @GetMapping("/getText")
    public CommonResult<String> getText(@RequestParam String url)
    {
        return myService.getText(url);
    }
}
