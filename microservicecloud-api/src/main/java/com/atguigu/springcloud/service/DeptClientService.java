package com.atguigu.springcloud.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atguigu.springcloud.entities.Dept;


/**
 * @author zzyy
 * @Description: 修改microservicecloud-api工程，根据已经有的DeptClientService接口
 * 新建一个实现了FallbackFactory接口的类DeptClientServiceFallbackFactory
 * @date 2018年4月21日
 */
//@FeignClient(value = "MICROSERVICECLOUD-DEPT")
//添加熔断机制，开启的时候是8001，而不是hystrix8001 因为hystrix8001 是单独配置方法的，通过在服务端验证。而这个服务降级是直接在客户端验证
@FeignClient(value = "MICROSERVICECLOUD-DEPT",fallbackFactory=DeptClientServiceFallbackFactory.class)
public interface DeptClientService {
    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    Dept get(@PathVariable("id") long id);

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    List<Dept> list();

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    boolean add(Dept dept);
}
