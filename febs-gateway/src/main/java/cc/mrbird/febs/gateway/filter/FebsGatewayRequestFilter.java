package cc.mrbird.febs.gateway.filter;

import cc.mrbird.febs.common.entity.FebsConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class FebsGatewayRequestFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //pre、post、route和error，我们要在请求转发出去前添加请求头，所以这里指定为pre
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //过滤器的优先级，数字越小，优先级越高
        return 6;
    }

    @Override
    public boolean shouldFilter() {
        //true时表示是否执行该过滤器的run方法，false则表示不执行
        return true;
    }

    /**
     * 定义过滤器的主要逻辑
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);
        HttpServletRequest request = ctx.getRequest();
        String host = request.getRemoteHost();
        String method = request.getMethod();
        String uri = request.getRequestURI();

        log.info("请求URI：{}，HTTP Method：{}，请求IP：{}，ServerId：{}", uri, method, host, serviceId);

        byte[] token = Base64Utils.encode((FebsConstant.ZUUL_TOKEN_VALUE).getBytes());
        ctx.addZuulRequestHeader(FebsConstant.ZUUL_TOKEN_HEADER, new String(token));
        return null;
    }
}
