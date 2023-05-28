package tomato.classifier.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import tomato.classifier.data.ResultData;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TomatoRepository {
    private static ResultData finalData = new ResultData();

    public ResultData save(ResultData data) {

        finalData = data;

        return finalData;
    }

    public ResultData find() {
        return finalData;
    }
}
