package dto.Request;

public class DeleteEntity extends TransferableObject {
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
