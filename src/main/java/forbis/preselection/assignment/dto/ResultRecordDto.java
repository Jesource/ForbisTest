package forbis.preselection.assignment.dto;

import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ResultRecordDto {
    private LocalDateTime timestamp;
    @Getter
    private List<String> tokenGroups;

    public ResultRecordDto(String record) {
        mapRecordString(record);
    }

    private void mapRecordString(String record) {
        if (record == null) {
            throw new IllegalArgumentException("Empty record string was passed");
        }
        try {
            JSONObject jsonObject = new JSONObject(record);
            setTimestamp(LocalDateTime.parse((CharSequence) jsonObject.get("timestamp")));
            setTokenGroups(jsonObject.getJSONArray("formatted_result"));
        } catch (JSONException e) {
            System.out.println("Failed to map to JSON object: " + e.getMessage());
        }
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setTokenGroups(JSONArray tokenGroups) {
        this.tokenGroups = new ArrayList<>();

        if (tokenGroups.isEmpty()) {
            return;
        }
        for (Object tokenGroup : tokenGroups) {
            this.tokenGroups.add((String) tokenGroup);
        }
    }
}
