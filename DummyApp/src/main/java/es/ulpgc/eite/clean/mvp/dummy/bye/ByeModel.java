package es.ulpgc.eite.clean.mvp.dummy.bye;

import es.ulpgc.eite.clean.mvp.GenericModel;


public class ByeModel extends GenericModel<Bye.ModelToPresenter>
        implements Bye.PresenterToModel {

    private String byeMsgText;
    private String sayByeBtnLabel;
    private String goToHelloBtnLabel;


    /**
     * Method that recovers a reference to the PRESENTER
     * You must ALWAYS call {@link super#onCreate(Object)} here
     *
     * @param presenter Presenter interface
     */
    @Override
    public void onCreate(Bye.ModelToPresenter presenter) {
        super.onCreate(presenter);

        sayByeBtnLabel = "Say Bye";
        byeMsgText = "Bye World!";
        goToHelloBtnLabel = "Go To Hello";
    }

    /**
     * Called by layer PRESENTER when VIEW pass for a reconstruction/destruction.
     * Usefull for kill/stop activities that could be running on the background Threads
     *
     * @param isChangingConfiguration Informs that a change is occurring on the configuration
     */
    @Override
    public void onDestroy(boolean isChangingConfiguration) {

    }

    ///////////////////////////////////////////////////////////////////////////////////
    // Presenter To Model ////////////////////////////////////////////////////////////


    @Override
    public void onChangeMsgByBtnClicked() {

    }

    @Override
    public String getByeMsg() {
        return byeMsgText;
    }

    @Override
    public String getGoToHelloBtnLabel() {
        return goToHelloBtnLabel;
    }

    @Override
    public String getSayByeBtnLabel() {
        return sayByeBtnLabel;
    }
}
