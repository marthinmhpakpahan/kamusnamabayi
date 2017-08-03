package ayusiahaan.kamusnamabayi;

/**
 * Created by user on 8/3/2017.
 */

public class BabyModel {
    public String name;
    public String defenition;

    public BabyModel(){}

    public BabyModel(String name, String defenition) {
        this.name = name;
        this.defenition = defenition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefenition() {
        return defenition;
    }

    public void setDefenition(String defenition) {
        this.defenition = defenition;
    }
}
