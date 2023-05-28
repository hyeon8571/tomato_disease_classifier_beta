package tomato.classifier.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tomato.classifier.data.ResultData;
import tomato.classifier.entity.Disease;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DiseaseDto {

    private String id;

    private String d_name;

    private String src;

    private String solution;

    private Integer prob;


    public static DiseaseDto convertDto(Disease target, ResultData data) {
        return new DiseaseDto(
                target.getId(),
                target.getD_name(),
                target.getSrc(),
                target.getSolution(),
                data.getProb()
        );
    }
}
