package com.yallagym.client.forms;

import com.cn2.util.cn1String;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.Preferences;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.CN;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.BrowserNavigationCallback;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.codename1.util.StringUtil;
import com.yallagym.APIs;
import com.yallagym.BaseForm;
import com.yallagym.client.dialogs.erorr_msg;
import com.yallagym.client.layouts.Correct_Msg;
import java.util.Date;

public class client_online_payment extends BaseForm {
    
    Resources res;
    APIs api = new APIs();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String id_gym;
    String type;
    String dateStart;
    Form frm;
    String idSession;
    String idUnique;
    
    public client_online_payment() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
        
    }
    
    public client_online_payment(com.codename1.ui.util.Resources res) {
         initGuiBuilderComponents(res);
    }
    
    public client_online_payment(com.codename1.ui.util.Resources res, Form frm, String id_gym, String type, String dateStart, String idSession, String idUnique, String amount) {
          initGuiBuilderComponents(res);
        this.res = res;
        this.frm = frm;
        this.id_gym = id_gym;
        this.type = type;
        this.dateStart = dateStart;
        this.idSession = idSession;
        this.idUnique = idUnique;
        installTitleArea(res);
        installBackCommand(frm);
        installBackIcon(frm);
        gui_Container.setScrollableY(false);
        String merchantID = "TEST9800047300";
//        int price = Integer.valueOf(amount)
        System.out.println("idSession:" + idSession);
        System.out.println("idUnique:" + idUnique);
        System.out.println("amount:" + amount);
        BrowserComponent browser = new BrowserComponent();
        String html = cn1String.format("<html> <head> <script src=\"https://mepspay.gateway.mastercard.com/checkout/version/54/checkout.js\" data-error=\"errorCallback\" data-cancel=\"cancelCallback\"></script> <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script> <script type=\"text/javascript\">  function errorCallback(error) { console.log(JSON.stringify(error)); } function cancelCallback() { console.log('Payment cancelled'); } openMepsTerminal(); function openMepsTerminal(data) { Checkout.configure({ merchant: '?', order: { amount: ?, currency: '?', description: '?', id: '?' }, interaction: { operation: 'PURCHASE', merchant: { name: 'Open Sky', address: { line1: 'Jordan', line2: 'Amman - khalda' } } } }); Checkout.showPaymentPage(); } </script> </head> <body> </body> </html>",
                merchantID,
                amount,
                "JOD",
                "Order Gym",
                idUnique);
        System.out.println(html);
//String html="<html> <head> <script src=\"https://mepspay.gateway.mastercard.com/checkout/version/54/checkout.js\" data-error=\"errorCallback\" data-cancel=\"cancelCallback\"></script> <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script> <script type=\"text/javascript\"> function errorCallback(error) { console.log(JSON.stringify(error)); } function cancelCallback() { console.log('Payment cancelled'); } openMepsTerminal(); function openMepsTerminal(data) { Checkout.configure({ merchant: 'TEST9800047300', order: { amount: 50, currency: 'JOD', description: 'Gym Order', id: '337837561' }, interaction: { operation: 'PURCHASE', merchant: { name: 'Open Sky', address: { line1: 'Jordan', line2: 'Amman - khalda' } } } }); Checkout.showPaymentPage(); } </script> </head> <body> </body> </html>";
//        String html = "<html> <head> <script src=\"https://mepspay.gateway.mastercard.com/checkout/version/54/checkout.js\" data-error=\"errorCallback\" data-cancel=\"cancelCallback\"></script> <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script> <script type=\"text/javascript\"> function errorCallback(error) { console.log(JSON.stringify(error)); } function cancelCallback() { console.log('Payment cancelled'); } openMepsTerminal(); function openMepsTerminal(data) { Checkout.configure({ merchant: '" + merchantID + "', order: { amount: " + amount + ", currency: 'JOD', description: 'Gym Order', id: '" + idUnique + "' }, interaction: { operation: 'PURCHASE', merchant: { name: 'Open Sky', address: { line1: 'Jordan', line2: 'Amman - khalda' } } } }); Checkout.showPaymentPage(); } </script> </head> <body> </body> </html>";
        browser.setPage(html, "http://yallagym.herokuapp.com");
//        browser.setPage("<html> <head> <style> .wrapper { text-align: center; } .button { top: 50%; padding: 100px; font-size: 40px; } </style> </head> <body> <div class=\"wrapper\"> <button herf onclick=\"window.location.href='http://yallagym.herokuapp.com/payment/callback/accepted'\" class=\"button\">Pay with Payment Page</button> <button herf onclick=\"window.location.href='http://yallagym.herokuapp.com/payment/callback/canceled'\" class=\"button\">Cancel</button> <button herf onclick=\"window.location.href='http://yallagym.herokuapp.com/payment/callback/error'\" class=\"button\">Error</button> </div> </body> </html>",
//                        "http://yallagym.herokuapp.com");
        //        browser.setURL("jar:///payment.html");
        browser.addBrowserNavigationCallback(new BrowserNavigationCallback() {
            @Override
            public boolean shouldNavigate(String url) {
                System.out.println("url:" + url);
              /*  if (url.equals("http://yallagym.herokuapp.com/payment/callback/accepted") || url.contains("/receipt/")) {
                    doAcceptedPayment();
                } else */ if (url.equals("http://yallagym.herokuapp.com/payment/callback/canceled") || url.equals("about://blank#__hc-action-cancel") || url.contains("action-cancel")) {
                    doCaneledPayment();
                } else if (url.equals("http://yallagym.herokuapp.com/payment/callback/error")) {
                    doErorrPayment();
                }
                return true;
            }
            
        });
        gui_Container.add(BorderLayout.CENTER, browser);
        
    }

////////////////////////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.ui.Container gui_Container = new com.codename1.ui.Container(new com.codename1.ui.layouts.LayeredLayout());


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableY(true);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("client_online_payment");
        setName("client_online_payment");
                gui_Container.setInlineStylesTheme(resourceObjectInstance);
        gui_Container.setName("Container");
        addComponent(gui_Container);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container.getParent().getLayout()).setInsets(gui_Container, "0.0mm 0.0mm 0.0mm 0.0mm").setReferenceComponents(gui_Container, "-1 -1 -1 -1").setReferencePositions(gui_Container, "0.0 0.0 0.0 0.0");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    private void doStartPayment() {
        
    }
    
    private void doAcceptedPayment() {
        System.out.println("doAcceptedPayment");
        CN.callSerially(() -> {
            newOnlineOrder();
        });
        
    }
    
    private void doCaneledPayment() {
        System.out.println("doCaneledPayment");
        CN.callSerially(() -> {
            frm.show();
//            InfiniteProgress prog = new InfiniteProgress();
//            Dialog dlg = prog.showInfiniteBlocking();
//            Util.sleep(500);
//            dlg.dispose();
        });
    }
    
    private void doErorrPayment() {
        System.out.println("doErorrPayment");
        CN.callSerially(() -> {
            new erorr_msg(res, "Unexpected error", "Please try again or try use another payment method. \nDo you have anther problem? tell us about that: support@yallagym.com", "Back To Order", e -> {
                frm.show();
            }).show();
        });
    }
    
    private void doEndPayment() {
        
    }
    
    private void newOnlineOrder() {
        new Form().show();
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInfiniteBlocking();
        Util.sleep(500);
        String username = Preferences.get("username", "null");
        String date_order = dateFormat.format(new Date());
        String id_g = StringUtil.replaceFirst(id_gym, ".0", "");
        api.createOnlineOrder(username, id_g, date_order, type, dateStart, idSession, idUnique);
        dlg.dispose();
        Command back = new Command("Back") {
            public void actionPerformed(ActionEvent ev) {
            }
        };
//        CN.getCurrentForm().setBackCommand(back);
//        this.setBackCommand(back);
        String msg = "";
        if (type.equals("price_d")) {
            msg = "Thank you for your submit, we will contact you within 5 min.";
        } else if (type.equals("price_w")) {
            msg = "Thank you for your submit, we will contact you within a few 24 hours.";
        } else {
            msg = "Thank you for your submit, we will contact you within a few 48 hours.";
        }
        
        Dialog dlg_correct_Msg = new Correct_Msg(res, "Sent successfully", msg, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new client_orders(res, new list_form(res)).show();
            }
        });
        dlg_correct_Msg.setDisposeWhenPointerOutOfBounds(false);
        dlg_correct_Msg.show();
        
    }

}