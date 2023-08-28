package tomato.classifier.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import tomato.classifier.dto.ResponseDto;

import javax.servlet.http.HttpServletResponse;

public class CustomResponseUtil {
    private static final Logger log = LoggerFactory.getLogger(CustomResponseUtil.class);

    public static void success(HttpServletResponse response, Object dto) {
        try {
            ObjectMapper om = new ObjectMapper();
            ResponseDto<?> responseDto = new ResponseDto<>(1, "로그인 성공", dto);
            String responseBody = om.writeValueAsString(responseDto); // json으로 변경
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().println(responseBody);
        } catch (Exception e) {
            log.error("서버 파싱 에러");
        }
    }

    public static void fail(HttpServletResponse response, String msg, HttpStatus httpStatus) {
        try {
//            ObjectMapper om = new ObjectMapper();
//            ResponseDto<?> responseDto = new ResponseDto<>(-1, msg, null);
//            String responseBody = om.writeValueAsString(responseDto); // json으로 변경, 지금은 페이지를 리턴해주므로 생략
//            response.setContentType("application/json; charset=utf-8");
//            response.getWriter().println(responseBody);
            response.sendRedirect("/err-page");
        } catch (Exception e) {
            log.error("서버 파싱 에러");
        }
    }


}