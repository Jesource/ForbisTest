package forbis.preselection.assignment.web.models;

import org.springframework.web.multipart.MultipartFile;


public class InputModel {
    private String textInput1;
    private String textInput2;
    private MultipartFile file1;
    private MultipartFile file2;

    public InputModel() {
    }

    public String getTextInput1() {
        return textInput1;
    }

    public void setTextInput1(String textInput1) {
        this.textInput1 = textInput1;
    }

    public String getTextInput2() {
        return textInput2;
    }

    public void setTextInput2(String textInput2) {
        this.textInput2 = textInput2;
    }

    public MultipartFile getFile1() {
        return file1;
    }

    public void setFile1(MultipartFile file1) {
        this.file1 = file1;
    }

    public MultipartFile getFile2() {
        return file2;
    }

    public void setFile2(MultipartFile file2) {
        this.file2 = file2;
    }
}
