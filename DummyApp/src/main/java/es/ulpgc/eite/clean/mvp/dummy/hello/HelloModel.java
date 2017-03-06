package es.ulpgc.eite.clean.mvp.dummy.hello;

import es.ulpgc.eite.clean.mvp.GenericModel;


public class HelloModel extends GenericModel<Hello.ModelToPresenter>
        implements Hello.PresenterToModel {

    private String helloMsgText;
    private String sayHelloBtnLabel;
    private String goToByeBtnLabel;

    /**
     * Method that recovers a reference to the PRESENTER
     * You must ALWAYS call {@link super#onCreate(Object)} here
     *
     * @param presenter Presenter interface
     */
    @Override
    public void onCreate(Hello.ModelToPresenter presenter) {
        super.onCreate(presenter);

        sayHelloBtnLabel = "Say Hello";
        helloMsgText = "Hello World!";
        goToByeBtnLabel = "Go To Bye";
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
    public void onChangeMsgByHelloBtnClicked() {

    }



    @Override
    public String getHelloMsg() {
        return helloMsgText;
    }

    @Override
    public String getGoToByeBtnLabel() {
        return goToByeBtnLabel;
    }

    @Override
    public String getSayHelloBtnLabel() {
        return sayHelloBtnLabel;
    }

}
