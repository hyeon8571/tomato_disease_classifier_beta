package tomato.classifier.entity;

public enum Role {
    USER("ROLE_USER"), MANAGER("ROLE_MANAGER"), ADMIN("ROLE_ADMIN");

    Role(String value) {this.value = value;}

    public String value;
}
