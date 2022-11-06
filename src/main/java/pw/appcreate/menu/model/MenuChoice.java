package pw.appcreate.menu.model;

/**
 * Created by woojen on 2020-05-24.
 */
public class MenuChoice {

    private boolean isConvertionImplementation;
    private boolean isBasicCrudImplementation;

    public MenuChoice(boolean isConvertionImplementation, boolean isBasicCrudImplementation) {
        this.isConvertionImplementation = isConvertionImplementation;
        this.isBasicCrudImplementation = isBasicCrudImplementation;
    }


    public boolean isConvertionImplementation() {
        return isConvertionImplementation;
    }

    public boolean isBasicCrudImplementation() {
        return isBasicCrudImplementation;
    }
}
