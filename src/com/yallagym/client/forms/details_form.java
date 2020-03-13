package com.yallagym.client.forms;

import com.cn2.ui.cn1Display;
import com.cn2.ui.cn1Slider;
import com.cn2.util.cn1String;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ToastBar;
import com.codename1.io.Preferences;
import com.codename1.io.Storage;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.CN;
import static com.codename1.ui.CN.getCurrentForm;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.RadioButton;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;
import com.codename1.util.StringUtil;
import com.yallagym.APIs;
import com.yallagym.BaseForm;
import com.yallagym.club.forms.club_APIs;
import com.yallagym.client.dialogs.erorr;
import com.yallagym.client.dialogs.erorr_msg;
import com.yallagym.client.dialogs.pay_dialog;
import com.yallagym.client.layouts.Correct_Msg;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class details_form extends BaseForm {

    Resources res;
    APIs api = new APIs();
    int index = 1;
    int list_size = 0;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Form current;
    Form frm;
    Image[] get_imgs;
    Dialog dlg;

    public details_form() {
        this(com.codename1.ui.util.Resources.getGlobalResources());

    }

    public details_form(com.codename1.ui.util.Resources res) {
        checkLanguage(res);
    }

    public details_form(com.codename1.ui.util.Resources res, Form frm, String id_gym, String type) {
        checkLanguage(res);
        this.res = res;
        current = this;
        this.frm = frm;
        this.getToolbar().setTitle("Club Information");
        this.getToolbar().setTitleCentered(true);
        installBackIcon(frm);
        installBackCommand(frm);
        this.setScrollableY(false);
        this.setScrollVisible(false);
        gui_Container_6.setVisible(false);
        gui_Infinite_Progress.setVisible(true);
//        new UITimer(() -> {
//            InfiniteProgress prog = new InfiniteProgress();
//            dlg = prog.showInfiniteBlocking();
        CN.callSerially(() -> {
            gui_Container_1.setFocusable(true);
            gui_Container_1.setScrollableY(true);
        });
        gui_Con_Image_View.setVisible(false);
        get_imgs = api.getGymImages(res, id_gym);
//        }).schedule(50, false, current);

        System.out.println(System.currentTimeMillis());
        new UITimer(() -> {
            System.out.println(System.currentTimeMillis());
            setValues(id_gym, type);
            gui_Container_1.setScrollableY(true);
            gui_Container_1.setScrollVisible(false);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            gui_date_picker.setFormatter(dateFormat);
            gui_date_picker.setDate(new Date());
            gui_date_picker.setUseLightweightPopup(true);
            int dimension = gui_date_picker.getPreferredH() / 3;
            gui_date_picker.setIcon(res.getImage("ic_datePiker.png").scaled(dimension, dimension));
            Date now = new Date();

            try {
                gui_date_picker.setStartDate(now);
                long one_year = (1000l * 60l * 60l * 24l * 365l);
                gui_date_picker.setEndDate(new Date(now.getTime() + one_year));
            } catch (Exception e) {
            }

            new ButtonGroup(gui_RB_1_DAYS, gui_RB_7_DAYS, gui_RB_30_DAYS);
//            gui_Infinite_Progress.setVisible(false);
            gui_Container_1.setVisible(true);
            gui_Con_Image_View.setVisible(true);

            System.out.println(System.currentTimeMillis());
            gui_Infinite_Progress.setVisible(false);
            gui_Container_6.setVisible(true);
            System.err.println("run");
            gui_date_picker.setGap(20);
            this.revalidate();
            gui_lbl_description.getAllStyles().setAlignment(CENTER);
        }).schedule(1000, false, current);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.ui.Container gui_Container_6 = new com.codename1.ui.Container(new com.codename1.ui.layouts.LayeredLayout());
    protected com.codename1.ui.Container gui_Con_Image_View = new com.codename1.ui.Container(new com.codename1.ui.layouts.LayeredLayout());
    protected com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.LayeredLayout());
    protected com.codename1.ui.Label gui_lbl_name = new com.codename1.ui.Label();
    protected com.codename1.ui.Container gui_Container = new com.codename1.ui.Container(new com.codename1.ui.layouts.LayeredLayout());
    protected com.codename1.ui.TextArea gui_lbl_description = new com.codename1.ui.TextArea();
    protected com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.LayeredLayout());
    protected com.codename1.ui.Label gui_Label_3 = new com.codename1.ui.Label();
    protected com.codename1.ui.Label gui_lbl_address = new com.codename1.ui.Label();
    protected com.codename1.ui.Label gui_Label_5 = new com.codename1.ui.Label();
    protected com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.LayeredLayout());
    protected com.codename1.ui.spinner.Picker gui_date_picker = new com.codename1.ui.spinner.Picker();
    protected com.codename1.ui.Container gui_Container_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.LayeredLayout());
    protected com.codename1.ui.Label gui_Label_6 = new com.codename1.ui.Label();
    protected com.codename1.ui.RadioButton gui_RB_1_DAYS = new com.codename1.ui.RadioButton();
    protected com.codename1.ui.RadioButton gui_RB_7_DAYS = new com.codename1.ui.RadioButton();
    protected com.codename1.ui.RadioButton gui_RB_30_DAYS = new com.codename1.ui.RadioButton();
    protected com.codename1.ui.Button gui_btn_book = new com.codename1.ui.Button();
    protected com.codename1.ui.Container gui_Container_5 = new com.codename1.ui.Container(new com.codename1.ui.layouts.LayeredLayout());
    protected com.codename1.ui.Label gui_Label_4 = new com.codename1.ui.Label();
    protected com.codename1.ui.Label gui_lbl_price = new com.codename1.ui.Label();
    protected com.codename1.components.InfiniteProgress gui_Infinite_Progress = new com.codename1.components.InfiniteProgress();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableY(true);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("details_form");
        setName("details_form");
        gui_Container_6.setPreferredSizeStr("inherit 32.804234mm");
                gui_Container_6.setInlineStylesTheme(resourceObjectInstance);
        gui_Container_6.setName("Container_6");
                gui_Infinite_Progress.setInlineStylesTheme(resourceObjectInstance);
        gui_Infinite_Progress.setName("Infinite_Progress");
        addComponent(gui_Container_6);
        gui_Con_Image_View.setPreferredSizeStr("67.72487mm 32.010582mm");
                gui_Con_Image_View.setInlineStylesTheme(resourceObjectInstance);
        gui_Con_Image_View.setInlineAllStyles("bgImage:null;");
        gui_Con_Image_View.setName("Con_Image_View");
        gui_Container_1.setPreferredSizeStr("inherit 82.27513mm");
        gui_Container_1.setUIID("Gray_Con");
                gui_Container_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Container_1.setName("Container_1");
        gui_Container_6.addComponent(gui_Con_Image_View);
        gui_Container_6.addComponent(gui_Container_1);
        gui_lbl_name.setPreferredSizeStr("69.31217mm 6.6137567mm");
        gui_lbl_name.setText("HAMMER GYM");
        gui_lbl_name.setUIID("PersonalInfo_Lbl");
                gui_lbl_name.setInlineStylesTheme(resourceObjectInstance);
        gui_lbl_name.setInlineAllStyles("fgColor:3c4646; alignment:center;");
        gui_lbl_name.setName("lbl_name");
        gui_Container.setPreferredSizeStr("78.30688mm 14.021164mm");
                gui_Container.setInlineStylesTheme(resourceObjectInstance);
        gui_Container.setName("Container");
        gui_Container_2.setPreferredSizeStr("inherit 5.820106mm");
                gui_Container_2.setInlineStylesTheme(resourceObjectInstance);
        gui_Container_2.setName("Container_2");
        gui_Label_5.setPreferredSizeStr("71.69312mm 5.291005mm");
        gui_Label_5.setText("Choose the start date:");
        gui_Label_5.setUIID("Details_Lbls");
                gui_Label_5.setInlineStylesTheme(resourceObjectInstance);
        gui_Label_5.setName("Label_5");
        gui_Container_3.setPreferredSizeStr("50.79365mm 6.0846562mm");
                gui_Container_3.setInlineStylesTheme(resourceObjectInstance);
        gui_Container_3.setName("Container_3");
        gui_Container_4.setPreferredSizeStr("74.07407mm 52.1164mm");
                gui_Container_4.setInlineStylesTheme(resourceObjectInstance);
        gui_Container_4.setName("Container_4");
        gui_Container_1.addComponent(gui_lbl_name);
        gui_Container_1.addComponent(gui_Container);
        gui_lbl_description.setPreferredSizeStr("58.73016mm 30.42328mm");
        gui_lbl_description.setEditable(false);
        gui_lbl_description.setEnabled(false);
        gui_lbl_description.setText("Description:  Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        gui_lbl_description.setUIID("Detalis_Sb_green");
                gui_lbl_description.setInlineStylesTheme(resourceObjectInstance);
        gui_lbl_description.setName("lbl_description");
        gui_lbl_description.setColumns(8);
        gui_lbl_description.setRows(1);
        gui_Container.addComponent(gui_lbl_description);
        ((com.codename1.ui.layouts.LayeredLayout)gui_lbl_description.getParent().getLayout()).setInsets(gui_lbl_description, "0.0mm 0.0mm 0.0mm 0.0mm").setReferenceComponents(gui_lbl_description, "-1 -1 -1 -1").setReferencePositions(gui_lbl_description, "0.0 0.0 0.0 0.0");
        gui_Container_1.addComponent(gui_Container_2);
        gui_Label_3.setPreferredSizeStr("13.756614mm 10.58201mm");
        gui_Label_3.setText("Address:");
        gui_Label_3.setUIID("Details_Lbls");
                gui_Label_3.setInlineStylesTheme(resourceObjectInstance);
        gui_Label_3.setName("Label_3");
        gui_lbl_address.setPreferredSizeStr("48.67725mm 10.31746mm");
        gui_lbl_address.setText("Amman, Sweileh");
        gui_lbl_address.setUIID("Detalis_Sb_green");
                gui_lbl_address.setInlineStylesTheme(resourceObjectInstance);
        gui_lbl_address.setName("lbl_address");
        gui_Container_2.addComponent(gui_Label_3);
        gui_Container_2.addComponent(gui_lbl_address);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Label_3.getParent().getLayout()).setInsets(gui_Label_3, "5.9604645E-8mm auto 0.0mm -3.5762787E-7mm").setReferenceComponents(gui_Label_3, "-1 -1 -1 -1").setReferencePositions(gui_Label_3, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_lbl_address.getParent().getLayout()).setInsets(gui_lbl_address, "0.0mm 0.0mm 0.0mm 0.0mm").setReferenceComponents(gui_lbl_address, "-1 -1 -1 0 ").setReferencePositions(gui_lbl_address, "0.0 0.0 0.0 1.0");
        gui_Container_1.addComponent(gui_Label_5);
        gui_Container_1.addComponent(gui_Container_3);
        gui_date_picker.setPreferredSizeStr("54.761906mm 10.8465605mm");
        gui_date_picker.setText("...");
        gui_date_picker.setUIID("datePicker");
                gui_date_picker.setInlineStylesTheme(resourceObjectInstance);
        gui_date_picker.setName("date_picker");
        gui_date_picker.setType(1);
        gui_Container_3.addComponent(gui_date_picker);
        ((com.codename1.ui.layouts.LayeredLayout)gui_date_picker.getParent().getLayout()).setInsets(gui_date_picker, "0.0mm 0.0mm 0.0mm 0.0mm").setReferenceComponents(gui_date_picker, "-1 -1 -1 -1").setReferencePositions(gui_date_picker, "0.0 0.0 0.0 0.0");
        gui_Container_1.addComponent(gui_Container_4);
        gui_Label_6.setPreferredSizeStr("71.69312mm inherit");
        gui_Label_6.setText("Choose your reservation type:");
        gui_Label_6.setUIID("Details_Lbls");
                gui_Label_6.setInlineStylesTheme(resourceObjectInstance);
        gui_Label_6.setName("Label_6");
        gui_RB_1_DAYS.setPreferredSizeStr("35.185184mm 4.2328043mm");
        gui_RB_1_DAYS.setText("       Booking for a day");
        gui_RB_1_DAYS.setUIID("Detalis_Sb_green");
                gui_RB_1_DAYS.setInlineStylesTheme(resourceObjectInstance);
        gui_RB_1_DAYS.setName("RB_1_DAYS");
        gui_RB_7_DAYS.setPreferredSizeStr("34.920635mm 4.7619047mm");
        gui_RB_7_DAYS.setText("       Booking for a week");
        gui_RB_7_DAYS.setUIID("Detalis_Sb_green");
                gui_RB_7_DAYS.setInlineStylesTheme(resourceObjectInstance);
        gui_RB_7_DAYS.setName("RB_7_DAYS");
        gui_RB_30_DAYS.setPreferredSizeStr("35.185184mm 4.4973545mm");
        gui_RB_30_DAYS.setText("       Booking for a month");
        gui_RB_30_DAYS.setUIID("Detalis_Sb_green");
                gui_RB_30_DAYS.setInlineStylesTheme(resourceObjectInstance);
        gui_RB_30_DAYS.setName("RB_30_DAYS");
        gui_btn_book.setPreferredSizeStr("35.185184mm 7.4074073mm");
        gui_btn_book.setText("Book now");
        gui_btn_book.setUIID("btn_get_yellow");
                gui_btn_book.setInlineStylesTheme(resourceObjectInstance);
        gui_btn_book.setName("btn_book");
        gui_Container_5.setPreferredSizeStr("64.55026mm 5.820106mm");
                gui_Container_5.setInlineStylesTheme(resourceObjectInstance);
        gui_Container_5.setName("Container_5");
        gui_Container_4.addComponent(gui_Label_6);
        gui_Container_4.addComponent(gui_RB_1_DAYS);
        gui_Container_4.addComponent(gui_RB_7_DAYS);
        gui_Container_4.addComponent(gui_RB_30_DAYS);
        gui_Container_4.addComponent(gui_btn_book);
        gui_Container_4.addComponent(gui_Container_5);
        gui_Label_4.setPreferredSizeStr("23.809525mm 10.58201mm");
        gui_Label_4.setText("Total price:");
        gui_Label_4.setUIID("Details_Lbls");
                gui_Label_4.setInlineStylesTheme(resourceObjectInstance);
        gui_Label_4.setName("Label_4");
        gui_lbl_price.setPreferredSizeStr("38.62434mm 10.31746mm");
        gui_lbl_price.setText("35 JOD");
        gui_lbl_price.setUIID("Detalis_Sb_green");
                gui_lbl_price.setInlineStylesTheme(resourceObjectInstance);
        gui_lbl_price.setName("lbl_price");
        gui_Container_5.addComponent(gui_Label_4);
        gui_Container_5.addComponent(gui_lbl_price);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Label_4.getParent().getLayout()).setInsets(gui_Label_4, "5.9604645E-8mm auto 0.0mm -3.5762787E-7mm").setReferenceComponents(gui_Label_4, "-1 -1 -1 -1").setReferencePositions(gui_Label_4, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_lbl_price.getParent().getLayout()).setInsets(gui_lbl_price, "0.0mm 0.0mm 0.0mm 0.0mm").setReferenceComponents(gui_lbl_price, "-1 -1 -1 0 ").setReferencePositions(gui_lbl_price, "0.0 0.0 0.0 1.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Label_6.getParent().getLayout()).setInsets(gui_Label_6, "-2.3841858E-7mm 0.0mm auto 0.0mm").setReferenceComponents(gui_Label_6, "-1 -1 -1 -1").setReferencePositions(gui_Label_6, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_RB_1_DAYS.getParent().getLayout()).setInsets(gui_RB_1_DAYS, "2.3841858E-7mm auto auto 2.910053mm").setReferenceComponents(gui_RB_1_DAYS, "0 -1 -1 -1").setReferencePositions(gui_RB_1_DAYS, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_RB_7_DAYS.getParent().getLayout()).setInsets(gui_RB_7_DAYS, "0.0mm auto auto 2.910053mm").setReferenceComponents(gui_RB_7_DAYS, "1 -1 -1 -1").setReferencePositions(gui_RB_7_DAYS, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_RB_30_DAYS.getParent().getLayout()).setInsets(gui_RB_30_DAYS, "0.0mm auto auto 2.910053mm").setReferenceComponents(gui_RB_30_DAYS, "2 -1 -1 -1").setReferencePositions(gui_RB_30_DAYS, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_btn_book.getParent().getLayout()).setInsets(gui_btn_book, "1.8518518mm auto auto auto").setReferenceComponents(gui_btn_book, "5 -1 -1 -1").setReferencePositions(gui_btn_book, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container_5.getLayout()).setPreferredWidthMM((float)64.55026);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container_5.getLayout()).setPreferredHeightMM((float)5.820106);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container_5.getParent().getLayout()).setInsets(gui_Container_5, "0.0mm 0.0mm auto 0.0mm").setReferenceComponents(gui_Container_5, "3 -1 -1 -1").setReferencePositions(gui_Container_5, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_lbl_name.getParent().getLayout()).setInsets(gui_lbl_name, "0.0mm 0.0mm auto 0.0mm").setReferenceComponents(gui_lbl_name, "-1 -1 -1 -1").setReferencePositions(gui_lbl_name, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container.getLayout()).setPreferredWidthMM((float)55.291004);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container.getLayout()).setPreferredHeightMM((float)14.021164);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container.getParent().getLayout()).setInsets(gui_Container, "0.0mm 0.0mm auto 0.0mm").setReferenceComponents(gui_Container, "0 -1 -1 -1").setReferencePositions(gui_Container, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container_2.getLayout()).setPreferredWidthMM((float)64.55026);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container_2.getLayout()).setPreferredHeightMM((float)5.820106);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container_2.getParent().getLayout()).setInsets(gui_Container_2, "0.0mm 0.0mm auto 0.0mm").setReferenceComponents(gui_Container_2, "1 -1 -1 -1").setReferencePositions(gui_Container_2, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Label_5.getParent().getLayout()).setInsets(gui_Label_5, "0.0mm 0.0mm auto 0.0mm").setReferenceComponents(gui_Label_5, "2 -1 -1 -1").setReferencePositions(gui_Label_5, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container_3.getLayout()).setPreferredWidthMM((float)50.79365);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container_3.getLayout()).setPreferredHeightMM((float)6.0846562);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container_3.getParent().getLayout()).setInsets(gui_Container_3, "0.0mm auto auto auto").setReferenceComponents(gui_Container_3, "3 -1 -1 -1").setReferencePositions(gui_Container_3, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container_4.getLayout()).setPreferredWidthMM((float)64.55026);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container_4.getLayout()).setPreferredHeightMM((float)52.1164);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container_4.getParent().getLayout()).setInsets(gui_Container_4, "0.0mm 0.0mm auto 0.0mm").setReferenceComponents(gui_Container_4, "4 -1 -1 -1").setReferencePositions(gui_Container_4, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Con_Image_View.getLayout()).setPreferredWidthMM((float)67.72487);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Con_Image_View.getLayout()).setPreferredHeightMM((float)32.010582);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Con_Image_View.getParent().getLayout()).setInsets(gui_Con_Image_View, "0.0mm 0.0mm 70.0% 0.0mm").setReferenceComponents(gui_Con_Image_View, "-1 -1 -1 -1").setReferencePositions(gui_Con_Image_View, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container_1.getLayout()).setPreferredWidthMM((float)67.72487);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container_1.getLayout()).setPreferredHeightMM((float)82.27513);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container_1.getParent().getLayout()).setInsets(gui_Container_1, "0.0mm 0.0mm 0.0mm 0.0mm").setReferenceComponents(gui_Container_1, "0 -1 -1 -1").setReferencePositions(gui_Container_1, "1.0 0.0 0.0 0.0");
        addComponent(gui_Infinite_Progress);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container_6.getLayout()).setPreferredWidthMM((float)64.28571);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container_6.getLayout()).setPreferredHeightMM((float)32.804234);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container_6.getParent().getLayout()).setInsets(gui_Container_6, "0.0mm 0.0mm 0.0mm 0.0mm").setReferenceComponents(gui_Container_6, "-1 -1 -1 -1").setReferencePositions(gui_Container_6, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Infinite_Progress.getParent().getLayout()).setInsets(gui_Infinite_Progress, "auto auto auto auto").setReferenceComponents(gui_Infinite_Progress, "-1 -1 -1 -1").setReferencePositions(gui_Infinite_Progress, "0.0 0.0 0.0 0.0");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Image imgs[];

    private void setValues(String id_gym, String type) {
        Map<String, Object> item = api.getGymAllInfo(res, id_gym);
        if (item != null) {
            String id = item.get("id") + "";
            String name = item.get("name") + "";
            String description = item.get("description") + "";
            String price_d = (Integer.valueOf(item.get("price_d").toString()) + Integer.valueOf(item.get("price_d_fees").toString())) + " JD";
            String price_w = (Integer.valueOf(item.get("price_w").toString()) + Integer.valueOf(item.get("price_w_fees").toString())) + " JD";
            String price_m = (Integer.valueOf(item.get("price_m").toString()) + Integer.valueOf(item.get("price_m_fees").toString())) + " JD";

            String rate = item.get("rate") + "";
            String address = item.get("address") + "";
            String work_time = item.get("work_time") + "";
//        String image = item.get("images") + "";
//        String images[] = cn1String.splite(image, ",");

            String coordinates = item.get("coordinates") + "";
            String[] adressSub = cn1String.splite(coordinates, ",");
            double lat = Double.valueOf(adressSub[0]);
            double lon = Double.valueOf(adressSub[1]);
            gui_lbl_name.setText(name);
            gui_lbl_description.setText(description);
            gui_lbl_address.setText(address);

            if (item.get("price_d").toString().equals("0")) {
                gui_RB_1_DAYS.setVisible(false);
            }
            if (item.get("price_w").toString().equals("0")) {
                gui_RB_7_DAYS.setVisible(false);
            }
            if (item.get("price_m").toString().equals("0")) {
                gui_RB_30_DAYS.setVisible(false);
            }

//        pay_dlg.addActionListener(e -> {
//        });
//
//        pay_dlg.setPreferredH(cn1Display.getScaledMax(12));
//        pay_dlg.getAllStyles().setMargin(30, 0, 20, 20);
            if (type.equals("price_d")) {
                gui_RB_1_DAYS.setSelected(true);
                gui_lbl_price.setText(price_d);
                Type_Seb = "price_d";
            } else if (type.equals("price_w")) {
                gui_RB_7_DAYS.setSelected(true);
                gui_lbl_price.setText(price_w);
                Type_Seb = "price_w";
            } else if (type.equals("price_m")) {
                gui_RB_30_DAYS.setSelected(true);
                gui_lbl_price.setText(price_m);
                Type_Seb = "price_m";
            }
            gui_RB_1_DAYS.addActionListener(e -> {
                gui_lbl_price.setText(price_d);
                Type_Seb = "price_d";
            });
            gui_RB_7_DAYS.addActionListener(e -> {
                gui_lbl_price.setText(price_w);
                Type_Seb = "price_w";
            });
            gui_RB_30_DAYS.addActionListener(e -> {
                gui_lbl_price.setText(price_m);
                Type_Seb = "price_m";
            });
            gui_date_picker.addActionListener(e -> {
                if (gui_date_picker.getDate().getTime() < new Date().getTime()) {
                    ToastBar.showErrorMessage("you can't choose time before current time");
                    gui_date_picker.setDate(new Date());
                }
            });

//            CN.callSerially(() -> {
//            new Thread() {
//                public void run() {
////                         InfiniteProgress prog = new InfiniteProgress();
////            Dialog dlg = prog.showInfiniteBlocking();
            CN.invokeAndBlock(() -> {
//                Image[] get_imgs = api.getGymImages(res, id);
                setImages(get_imgs);
            });
////                    dlg.dispose();
//                }
//            }.start();

            gui_btn_book.addActionListener(e -> {
                boolean checkTime = checkIsTime();
                System.out.println("checkTime: " + checkTime);
                if (checkTime) {
                    int amount = Integer.valueOf(StringUtil.replaceFirst(gui_lbl_price.getText(), " JD", ""));
                    if (Type_Seb.equals("price_d")) {
                        InfiniteProgress prog = new InfiniteProgress();
                        Dialog dlg = prog.showInfiniteBlocking();
                        boolean isHaveAnotherOrder = api.checkHaveOrder(gui_date_picker.getDate().getTime() + "");
                        dlg.dispose();
                        if (isHaveAnotherOrder) {
                            new erorr_msg(res, "Warning", "You have already ordered in this date", "Ok", null).show();
                        } else {
                            new pay_dialog(res, id_gym, Type_Seb, gui_date_picker.getDate().getTime() + "", amount + "").show(cn1Display.getHeight() - cn1Display.getHeight() / 3, 0, 0, 0);
                        }
                    } else {
                        new pay_dialog(res, id_gym, Type_Seb, gui_date_picker.getDate().getTime() + "", amount + "").show(cn1Display.getHeight() - cn1Display.getHeight() / 3, 0, 0, 0);
                    }
                } else {
                    ToastBar.showErrorMessage("Error, can't book start date before now date.");
                }
            });
        } else {
            frm.show();
            new erorr_msg(res, "Unavailable", "Sorry, this gym is not available until now", "Ok", e -> {
                new list_form(res).show();
            }, true).show();
        }

    }
    String Type_Seb = "";

    private void setImages(Image[] get_imgs) {
        if (get_imgs.equals(null) || get_imgs.length == 0 || get_imgs.equals("null")) {
            imgs = new Image[]{res.getImage("Placeholder-1.jpg")};
        } else {
            imgs = get_imgs;
        }
        gui_Con_Image_View.getAllStyles().setBgImage(imgs[0]);

        if (imgs.length > 2) {
            new UITimer(() -> {
                if (index < imgs.length) {
                    gui_Con_Image_View.getAllStyles().setBgImage(imgs[index]);
                    gui_Con_Image_View.revalidate();
                    index++;
                } else {
                    index = 0;
                    gui_Con_Image_View.getAllStyles().setBgImage(imgs[index]);
                    gui_Con_Image_View.revalidate();
                    index++;
                }
                current.revalidate();
            }).schedule(5000, true, this);
        }

    }

    private boolean checkIsTime() {
        System.out.println("gui_date_picker.getDate().getTime(): " + gui_date_picker.getDate().getTime());
        System.out.println("new Date().getTime(): " + new Date().getTime());
        return (gui_date_picker.getDate().getTime() + 60000l) >= new Date().getTime();
    }

    private void checkLanguage(Resources res) {
        if (com.yallagym.BaseForm.isArabic()) {
            initArGuiBuilderComponents(res);
            setRTLCmb(res);
        } else {
            initGuiBuilderComponents(res);
        }
    }

    private void initArGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableY(true);
        setInlineStylesTheme(resourceObjectInstance);
        setTitle("details_form");
        setName("details_form");
        gui_Container_6.setPreferredSizeStr("inherit 32.804234mm");
        gui_Container_6.setInlineStylesTheme(resourceObjectInstance);
        gui_Container_6.setName("Container_6");
        gui_Infinite_Progress.setInlineStylesTheme(resourceObjectInstance);
        gui_Infinite_Progress.setName("Infinite_Progress");
        addComponent(gui_Container_6);
        gui_Con_Image_View.setPreferredSizeStr("67.72487mm 32.010582mm");
        gui_Con_Image_View.setInlineStylesTheme(resourceObjectInstance);
        gui_Con_Image_View.setInlineAllStyles("bgImage:null;");
        gui_Con_Image_View.setName("Con_Image_View");
        gui_Container_1.setPreferredSizeStr("inherit 82.27513mm");
        gui_Container_1.setUIID("Gray_Con");
        gui_Container_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Container_1.setName("Container_1");
        gui_Container_6.addComponent(gui_Con_Image_View);
        gui_Container_6.addComponent(gui_Container_1);
        gui_lbl_name.setPreferredSizeStr("69.31217mm 6.6137567mm");
        gui_lbl_name.setText("HAMMER GYM");
        gui_lbl_name.setUIID("PersonalInfo_Lbl");
        gui_lbl_name.setInlineStylesTheme(resourceObjectInstance);
        gui_lbl_name.setInlineAllStyles("fgColor:3c4646; alignment:center;");
        gui_lbl_name.setName("lbl_name");
        gui_Container.setPreferredSizeStr("78.30688mm 14.021164mm");
        gui_Container.setInlineStylesTheme(resourceObjectInstance);
        gui_Container.setName("Container");
        gui_Container_2.setPreferredSizeStr("inherit 5.820106mm");
        gui_Container_2.setRTL(false);
        gui_Container_2.setInlineStylesTheme(resourceObjectInstance);
        gui_Container_2.setName("Container_2");
        gui_Label_5.setPreferredSizeStr("71.69312mm 5.291005mm");
        gui_Label_5.setRTL(true);
        gui_Label_5.setText("Choose the start date:");
        gui_Label_5.setUIID("Details_Lbls");
        gui_Label_5.setInlineStylesTheme(resourceObjectInstance);
        gui_Label_5.setName("Label_5");
        gui_Container_3.setPreferredSizeStr("50.79365mm 6.0846562mm");
        gui_Container_3.setRTL(false);
        gui_Container_3.setInlineStylesTheme(resourceObjectInstance);
        gui_Container_3.setName("Container_3");
        gui_Container_4.setPreferredSizeStr("74.07407mm 52.1164mm");
        gui_Container_4.setInlineStylesTheme(resourceObjectInstance);
        gui_Container_4.setName("Container_4");
        gui_Container_1.addComponent(gui_lbl_name);
        gui_Container_1.addComponent(gui_Container);
        gui_lbl_description.setPreferredSizeStr("58.73016mm 30.42328mm");
        gui_lbl_description.setEditable(false);
        gui_lbl_description.setEnabled(false);
        gui_lbl_description.setRTL(true);
        gui_lbl_description.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        gui_lbl_description.setUIID("Detalis_Sb_green");
        gui_lbl_description.setInlineStylesTheme(resourceObjectInstance);
        gui_lbl_description.setInlineAllStyles("alignment:center;");
        gui_lbl_description.setName("lbl_description");
        gui_lbl_description.setColumns(8);
        gui_lbl_description.setRows(1);
        gui_Container.addComponent(gui_lbl_description);
        ((com.codename1.ui.layouts.LayeredLayout) gui_lbl_description.getParent().getLayout()).setInsets(gui_lbl_description, "0.0mm 0.0mm 0.0mm 0.0mm").setReferenceComponents(gui_lbl_description, "-1 -1 -1 -1").setReferencePositions(gui_lbl_description, "0.0 0.0 0.0 0.0");
        gui_Container_1.addComponent(gui_Container_2);
        gui_Label_3.setPreferredSizeStr("10.58201mm 10.58201mm");
        gui_Label_3.setRTL(true);
        gui_Label_3.setText("Address:");
        gui_Label_3.setUIID("Details_Lbls");
        gui_Label_3.setInlineStylesTheme(resourceObjectInstance);
        gui_Label_3.setName("Label_3");
        gui_lbl_address.setPreferredSizeStr("21.693121mm 10.31746mm");
        gui_lbl_address.setRTL(true);
        gui_lbl_address.setText("Amman, Sweileh");
        gui_lbl_address.setUIID("Detalis_Sb_green");
        gui_lbl_address.setInlineStylesTheme(resourceObjectInstance);
        gui_lbl_address.setName("lbl_address");
        gui_Container_2.addComponent(gui_Label_3);
        gui_Container_2.addComponent(gui_lbl_address);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Label_3.getParent().getLayout()).setInsets(gui_Label_3, "5.9604645E-8mm 1.0mm 0.0mm auto").setReferenceComponents(gui_Label_3, "-1 -1 -1 -1").setReferencePositions(gui_Label_3, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout) gui_lbl_address.getParent().getLayout()).setInsets(gui_lbl_address, "0.0mm 0.0mm 0.0mm 0.0mm").setReferenceComponents(gui_lbl_address, "-1 0 -1 -1").setReferencePositions(gui_lbl_address, "0.0 1.0 0.0 0.0");
        gui_Container_1.addComponent(gui_Label_5);
        gui_Container_1.addComponent(gui_Container_3);
        gui_date_picker.setPreferredSizeStr("54.761906mm 10.8465605mm");
        gui_date_picker.setRTL(true);
        gui_date_picker.setText("...");
        gui_date_picker.setUIID("datePicker");
        gui_date_picker.setInlineStylesTheme(resourceObjectInstance);
        gui_date_picker.setName("date_picker");
        gui_date_picker.setType(1);
        gui_Container_3.addComponent(gui_date_picker);
        ((com.codename1.ui.layouts.LayeredLayout) gui_date_picker.getParent().getLayout()).setInsets(gui_date_picker, "0.0mm 0.0mm 0.0mm 0.0mm").setReferenceComponents(gui_date_picker, "-1 -1 -1 -1").setReferencePositions(gui_date_picker, "0.0 0.0 0.0 0.0");
        gui_Container_1.addComponent(gui_Container_4);
        gui_Label_6.setPreferredSizeStr("71.69312mm inherit");
        gui_Label_6.setRTL(true);
        gui_Label_6.setText("Choose your reservation type:");
        gui_Label_6.setUIID("Details_Lbls");
        gui_Label_6.setInlineStylesTheme(resourceObjectInstance);
        gui_Label_6.setName("Label_6");
        gui_RB_1_DAYS.setPreferredSizeStr("35.185184mm 4.2328043mm");
        gui_RB_1_DAYS.setRTL(true);
        gui_RB_1_DAYS.setText("Booking for a day");
        gui_RB_1_DAYS.setUIID("Detalis_Sb_green");
        gui_RB_1_DAYS.setInlineStylesTheme(resourceObjectInstance);
        gui_RB_1_DAYS.setName("RB_1_DAYS");
        gui_RB_1_DAYS.setGap(20);
        gui_RB_7_DAYS.setPreferredSizeStr("34.920635mm 4.7619047mm");
        gui_RB_7_DAYS.setRTL(true);
        gui_RB_7_DAYS.setText("Booking for a week");
        gui_RB_7_DAYS.setUIID("Detalis_Sb_green");
        gui_RB_7_DAYS.setInlineStylesTheme(resourceObjectInstance);
        gui_RB_7_DAYS.setName("RB_7_DAYS");
        gui_RB_7_DAYS.setGap(20);
        gui_RB_30_DAYS.setPreferredSizeStr("35.185184mm 4.4973545mm");
        gui_RB_30_DAYS.setRTL(true);
        gui_RB_30_DAYS.setText("Booking for a month");
        gui_RB_30_DAYS.setUIID("Detalis_Sb_green");
        gui_RB_30_DAYS.setInlineStylesTheme(resourceObjectInstance);
        gui_RB_30_DAYS.setName("RB_30_DAYS");
        gui_RB_30_DAYS.setGap(20);
        gui_btn_book.setPreferredSizeStr("35.185184mm 7.4074073mm");
        gui_btn_book.setText("Book now");
        gui_btn_book.setUIID("btn_get_yellow");
        gui_btn_book.setInlineStylesTheme(resourceObjectInstance);
        gui_btn_book.setName("btn_book");
        gui_Container_5.setPreferredSizeStr("64.55026mm 5.820106mm");
        gui_Container_5.setInlineStylesTheme(resourceObjectInstance);
        gui_Container_5.setName("Container_5");
        gui_Container_4.addComponent(gui_Label_6);
        gui_Container_4.addComponent(gui_RB_1_DAYS);
        gui_Container_4.addComponent(gui_RB_7_DAYS);
        gui_Container_4.addComponent(gui_RB_30_DAYS);
        gui_Container_4.addComponent(gui_btn_book);
        gui_Container_4.addComponent(gui_Container_5);
        gui_Label_4.setPreferredSizeStr("23.809525mm 10.58201mm");
        gui_Label_4.setRTL(true);
        gui_Label_4.setText("Total price:");
        gui_Label_4.setUIID("Details_Lbls");
        gui_Label_4.setInlineStylesTheme(resourceObjectInstance);
        gui_Label_4.setName("Label_4");
        gui_lbl_price.setPreferredSizeStr("24.338625mm 10.31746mm");
        gui_lbl_price.setRTL(true);
        gui_lbl_price.setText("35 JOD");
        gui_lbl_price.setUIID("Detalis_Sb_green");
        gui_lbl_price.setInlineStylesTheme(resourceObjectInstance);
        gui_lbl_price.setName("lbl_price");
        gui_Container_5.addComponent(gui_Label_4);
        gui_Container_5.addComponent(gui_lbl_price);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Label_4.getParent().getLayout()).setInsets(gui_Label_4, "5.9604645E-8mm 0.0mm 0.0mm auto").setReferenceComponents(gui_Label_4, "-1 -1 -1 -1").setReferencePositions(gui_Label_4, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout) gui_lbl_price.getParent().getLayout()).setInsets(gui_lbl_price, "0.0mm 0.0mm 0.0mm 0.0mm").setReferenceComponents(gui_lbl_price, "-1 0 -1 -1").setReferencePositions(gui_lbl_price, "0.0 1.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout) gui_Label_6.getParent().getLayout()).setInsets(gui_Label_6, "1.0mm 1.0mm auto 0.0mm").setReferenceComponents(gui_Label_6, "-1 -1 -1 -1").setReferencePositions(gui_Label_6, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout) gui_RB_1_DAYS.getParent().getLayout()).setInsets(gui_RB_1_DAYS, "2.3841858E-7mm 2.910053mm auto auto").setReferenceComponents(gui_RB_1_DAYS, "0 -1 -1 -1").setReferencePositions(gui_RB_1_DAYS, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout) gui_RB_7_DAYS.getParent().getLayout()).setInsets(gui_RB_7_DAYS, "0.0mm 2.910053mm auto auto").setReferenceComponents(gui_RB_7_DAYS, "1 -1 -1 -1").setReferencePositions(gui_RB_7_DAYS, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout) gui_RB_30_DAYS.getParent().getLayout()).setInsets(gui_RB_30_DAYS, "0.0mm 2.910053mm auto auto").setReferenceComponents(gui_RB_30_DAYS, "2 -1 -1 -1").setReferencePositions(gui_RB_30_DAYS, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout) gui_btn_book.getParent().getLayout()).setInsets(gui_btn_book, "1.8518518mm auto auto auto").setReferenceComponents(gui_btn_book, "5 -1 -1 -1").setReferencePositions(gui_btn_book, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container_5.getLayout()).setPreferredWidthMM((float) 64.55026);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container_5.getLayout()).setPreferredHeightMM((float) 5.820106);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container_5.getParent().getLayout()).setInsets(gui_Container_5, "0.0mm 1.0mm auto 0.0mm").setReferenceComponents(gui_Container_5, "3 -1 -1 -1").setReferencePositions(gui_Container_5, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout) gui_lbl_name.getParent().getLayout()).setInsets(gui_lbl_name, "0.0mm 0.0mm auto 0.0mm").setReferenceComponents(gui_lbl_name, "-1 -1 -1 -1").setReferencePositions(gui_lbl_name, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container.getLayout()).setPreferredWidthMM((float) 55.291004);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container.getLayout()).setPreferredHeightMM((float) 14.021164);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container.getParent().getLayout()).setInsets(gui_Container, "0.0mm 0.0mm auto 0.0mm").setReferenceComponents(gui_Container, "0 -1 -1 -1").setReferencePositions(gui_Container, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container_2.getLayout()).setPreferredWidthMM((float) 64.55026);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container_2.getLayout()).setPreferredHeightMM((float) 5.820106);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container_2.getParent().getLayout()).setInsets(gui_Container_2, "0.0mm 0.0mm auto 0.0mm").setReferenceComponents(gui_Container_2, "1 -1 -1 -1").setReferencePositions(gui_Container_2, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout) gui_Label_5.getParent().getLayout()).setInsets(gui_Label_5, "0.0mm 1.0mm auto 0.0mm").setReferenceComponents(gui_Label_5, "2 -1 -1 -1").setReferencePositions(gui_Label_5, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container_3.getLayout()).setPreferredWidthMM((float) 50.79365);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container_3.getLayout()).setPreferredHeightMM((float) 6.0846562);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container_3.getParent().getLayout()).setInsets(gui_Container_3, "0.0mm auto auto auto").setReferenceComponents(gui_Container_3, "3 -1 -1 -1").setReferencePositions(gui_Container_3, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container_4.getLayout()).setPreferredWidthMM((float) 64.55026);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container_4.getLayout()).setPreferredHeightMM((float) 52.1164);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container_4.getParent().getLayout()).setInsets(gui_Container_4, "0.0mm 0.0mm auto 0.0mm").setReferenceComponents(gui_Container_4, "4 -1 -1 -1").setReferencePositions(gui_Container_4, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout) gui_Con_Image_View.getLayout()).setPreferredWidthMM((float) 67.72487);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Con_Image_View.getLayout()).setPreferredHeightMM((float) 32.010582);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Con_Image_View.getParent().getLayout()).setInsets(gui_Con_Image_View, "0.0mm 0.0mm 70.0% 0.0mm").setReferenceComponents(gui_Con_Image_View, "-1 -1 -1 -1").setReferencePositions(gui_Con_Image_View, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container_1.getLayout()).setPreferredWidthMM((float) 67.72487);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container_1.getLayout()).setPreferredHeightMM((float) 82.27513);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container_1.getParent().getLayout()).setInsets(gui_Container_1, "0.0mm 0.0mm 0.0mm 0.0mm").setReferenceComponents(gui_Container_1, "0 -1 -1 -1").setReferencePositions(gui_Container_1, "1.0 0.0 0.0 0.0");
        addComponent(gui_Infinite_Progress);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container_6.getLayout()).setPreferredWidthMM((float) 64.28571);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container_6.getLayout()).setPreferredHeightMM((float) 32.804234);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container_6.getParent().getLayout()).setInsets(gui_Container_6, "0.0mm 0.0mm 0.0mm 0.0mm").setReferenceComponents(gui_Container_6, "-1 -1 -1 -1").setReferencePositions(gui_Container_6, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout) gui_Infinite_Progress.getParent().getLayout()).setInsets(gui_Infinite_Progress, "auto auto auto auto").setReferenceComponents(gui_Infinite_Progress, "-1 -1 -1 -1").setReferencePositions(gui_Infinite_Progress, "0.0 0.0 0.0 0.0");
    }// </editor-fold>

    private void setRTLCmb(Resources res) {
    }
}
