package project.core.api.pojo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = Player.Builder.class)
public class Player {
    private final Integer id;
    private final String name;
    private final Integer length;
    private final String brand;
    @JsonProperty("player_title")
    private final String playerTitle;

    private Player(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.length = builder.length;
        this.brand = builder.brand;
        this.playerTitle = builder.playerTitle;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getLength() {
        return length;
    }

    public String getBrand() {
        return brand;
    }

    public String getPlayerTitle() {
        return playerTitle;
    }


    public boolean equalsIgnoringId(Player player) {
        if (this == player) return true;
        return Objects.equals(getName(), player.getName()) &&
                Objects.equals(getLength(), player.getLength()) &&
                Objects.equals(getBrand(), player.getBrand()) &&
                Objects.equals(getPlayerTitle(), player.getPlayerTitle());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        return Objects.equals(getId(), player.getId()) &&
                Objects.equals(getName(), player.getName()) &&
                Objects.equals(getLength(), player.getLength()) &&
                Objects.equals(getBrand(), player.getBrand()) &&
                Objects.equals(getPlayerTitle(), player.getPlayerTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLength(), getBrand(), getPlayerTitle());
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", brand='" + brand + '\'' +
                ", playerTitle='" + playerTitle + '\'' +
                '}';
    }

    public static class Builder {
        private Integer id;
        private String name;
        private Integer length;
        private String brand;
        private String playerTitle;

        @JsonProperty("id")
        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        @JsonProperty("name")
        public Builder setMandatoryName(String name) {
            this.name = name;
            return this;
        }

        @JsonProperty("length")
        public Builder setOptionalLength(Integer length) {
            this.length = length;
            return this;
        }

        @JsonProperty("brand")
        public Builder setOptionalBrand(String brand) {
            this.brand = brand;
            return this;
        }

        @JsonProperty("player_title")
        public Builder setMandatoryPlayerTitle(String playerTitle) {
            this.playerTitle = playerTitle;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }
}
