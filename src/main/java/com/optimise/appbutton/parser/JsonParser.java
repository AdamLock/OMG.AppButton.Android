package com.optimise.appbutton.parser;

import android.util.Log;

import com.optimise.appbutton.model.Button;
import com.optimise.appbutton.model.ButtonMetadata;
import com.optimise.appbutton.model.ButtonPreview;
import com.optimise.appbutton.model.ButtonResponse;
import com.optimise.appbutton.model.ButtonStyle;
import com.optimise.appbutton.model.ButtonsList;
import com.optimise.appbutton.model.Card;
import com.optimise.appbutton.model.CardBody;
import com.optimise.appbutton.model.CardBodyItem;
import com.optimise.appbutton.model.CardBodyStyle;
import com.optimise.appbutton.model.CardFooter;
import com.optimise.appbutton.model.CardFooterStyle;
import com.optimise.appbutton.model.CardHeader;
import com.optimise.appbutton.model.CardHeaderStyle;
import com.optimise.appbutton.utility.network.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anoop.singh on 01-May-17.
 */

public class JsonParser {

    private static final String TAG = "OptimiseButtonParser";

    /**
     * Method will parse all the button related details.
     * @param response
     * @return
     */
    public static ButtonResponse getButtonResponse(Response response, int[] placementId){
        ButtonResponse buttonResponse = new ButtonResponse();
        List<ButtonsList> buttonsArrayList = new ArrayList<>();
        String jsonResult = new String(response.getResponseData());
        System.out.println("Button Response :" + jsonResult);
        Log.i("Button Response: ",  jsonResult);

        try{

            JSONObject apiResponseMessage = new JSONObject(jsonResult);
            JSONObject metadata = apiResponseMessage.getJSONObject("metaData");
            buttonResponse.setInfo(metadata.optString("info"));
            buttonResponse.setStatus(metadata.optString("status"));
            buttonResponse.setAdditionalInfo(metadata.optString("additionalInfo"));

            JSONObject getButtonsResponse = apiResponseMessage.getJSONObject("response");
            buttonResponse.setRequestId(getButtonsResponse.optString("requestId"));
            JSONObject buttonsObject = getButtonsResponse.getJSONObject("buttons");
            for (int k =0; k< buttonsObject.length(); k++) {
                ButtonsList buttonsList = new ButtonsList();
                JSONArray buttonsArray = buttonsObject.getJSONArray(String.valueOf(placementId[k]));
                int buttonsListSize = buttonsArray.length();
                for (int i = 0; i < buttonsListSize; i++) {
                    Button button = new Button();
                    //Retrieve buttons object from buttonsArray
                    JSONObject buttons = buttonsArray.getJSONObject(i);
                    button.setButtonId(buttons.optInt("id"));
                    button.setDisplayIndex(buttons.optInt("displayIndex"));

                    //Retrieve button metadata from buttons object
                    JSONObject metaData = buttons.getJSONObject("metaData");
                    ButtonMetadata buttonMetadata = new ButtonMetadata();
                    buttonMetadata.setAppName(metaData.optString("appName"));
                    buttonMetadata.setStoreId(metaData.optString("storeId"));
                    buttonMetadata.setDeepLinkingProtocol(metaData.optString("deepLinkProtocol"));
                    //Set metadata to button object
                    button.setMetadata(buttonMetadata);

                    //Retrieve ctaButton from buttons object
                    JSONObject ctaButton = buttons.getJSONObject("ctaButton");
                    ButtonPreview buttonPreview = new ButtonPreview();
                    buttonPreview.setLabel(ctaButton.optString("label"));
                    buttonPreview.setImageUri(ctaButton.optString("imageUri"));

                    //Retrieve Button style from ctaButton object
                    JSONObject styleObject = ctaButton.getJSONObject("style");
                    ButtonStyle buttonStyle = new ButtonStyle();
                    buttonStyle.setBackgroundColor(styleObject.optString("backgroundColor"));
                    buttonStyle.setBorderColor(styleObject.optString("borderColor"));
                    buttonStyle.setBorderStyle(styleObject.optString("borderStyle"));
                    buttonStyle.setBorderWidth(styleObject.optString("borderWidth"));
                    buttonStyle.setTextColor(styleObject.optString("textColor"));
                    buttonStyle.setFontSize(styleObject.optString("fontSize"));
                    buttonPreview.setButtonStyle(buttonStyle);
                    //Set buttonPreview to button object
                    button.setCtaButton(buttonPreview);

                    //Retrieve card from buttons object
                    JSONObject cardObject = buttons.getJSONObject("card");
                    Card card = new Card();
                    card.setCardType(cardObject.optString("cardType"));

                    //Retrieve header object from card Object
                    JSONObject cardHeaderObject = cardObject.getJSONObject("header");
                    CardHeader cardHeader = new CardHeader();
                    cardHeader.setPrimaryText(cardHeaderObject.optString("primaryText"));

                    //Retrieve card header style from cardHeader object
                    JSONObject cardHeaderStyleObject = cardHeaderObject.getJSONObject("style");
                    CardHeaderStyle cardHeaderStyle = new CardHeaderStyle();
                    cardHeaderStyle.setBackgroundColor(cardHeaderStyleObject.optString("backgroundColor"));
                    cardHeaderStyle.setPrimaryTextColor(cardHeaderStyleObject.optString("primaryTextColor"));
                    cardHeaderStyle.setPrimaryTextFontSize(cardHeaderStyleObject.optString("primaryTextFontSize"));
                    //Set cardHeaderStyle to cardHeader Object
                    cardHeader.setCardHeaderStyle(cardHeaderStyle);
                    card.setCardHeader(cardHeader);

                    //Retrieve card body from cardObject
                    JSONObject cardBodyObject = cardObject.getJSONObject("body");
                    CardBody cardBody = new CardBody();
                    cardBody.setAction(cardBodyObject.optString("action"));

                    //Retrieve CardBody style from cardBodyObject
                    JSONObject cardBodyStyleObject = cardBodyObject.getJSONObject("style");
                    CardBodyStyle cardBodyStyle = new CardBodyStyle();
                    cardBodyStyle.setBackgroundColor(cardBodyStyleObject.optString("backgroundColor"));
                    cardBodyStyle.setPrimaryTextColor(cardBodyStyleObject.optString("primaryTextColor"));
                    cardBodyStyle.setSecondaryTextColor(cardBodyStyleObject.optString("secondaryTextColor"));
                    cardBodyStyle.setPrimaryTextFontSize(cardBodyStyleObject.optString("primaryTextFontSize"));
                    cardBodyStyle.setSecondaryTextFontSize(cardBodyStyleObject.optString("secondaryTextFontSize"));
                    cardBodyStyle.setItemDividerHeight(cardBodyStyleObject.optString("itemDividerHeight"));
                    cardBodyStyle.setItemDividerColor(cardBodyStyleObject.optString("itemDividerColor"));
                    cardBodyStyle.setItemDividerStyle(cardBodyStyleObject.optString("itemDividerStyle"));
                    //Set cardBodyStyle in cardBody object
                    cardBody.setCardBodyStyle(cardBodyStyle);

                    //Retrieve CardItem array from cardBody Object
                    JSONArray cardItemArray = cardBodyObject.getJSONArray("items");
                    List<CardBodyItem> cardBodyItemList = new ArrayList<>();
                    int cardArrayLength = cardItemArray.length();
                    for (int j = 0; j < cardArrayLength; j++) {
                        CardBodyItem cardBodyItem = new CardBodyItem();
                        JSONObject cardItemObject = cardItemArray.getJSONObject(j);
                        cardBodyItem.setImageUri(cardItemObject.optString("imageUri"));
                        cardBodyItem.setPrimaryText(cardItemObject.optString("primaryText"));
                        cardBodyItem.setSecondaryText(cardItemObject.optString("secondaryText"));
                        cardBodyItem.setDeepLink(cardItemObject.optString("deepLink"));
                        cardBodyItemList.add(cardBodyItem);
                    }
                    //Add cardBodyItem list to cardBodyObject
                    cardBody.setCardBodyItem(cardBodyItemList);
                    card.setCardBody(cardBody);

                    //Retrieve footer from cardObject
                    JSONObject cardFooterObject = cardObject.getJSONObject("footer");
                    CardFooter cardFooter = new CardFooter();
                    cardFooter.setPrimaryText(cardFooterObject.optString("primaryText"));
                    cardFooter.setImageUri(cardFooterObject.optString("imageUri"));

                    //Retrieve CardBody style from cardBodyObject
                    JSONObject cardFooterStyleObject = cardHeaderObject.getJSONObject("style");
                    CardFooterStyle cardFooterStyle = new CardFooterStyle();
                    cardFooterStyle.setBackgroundColor(cardFooterStyleObject.optString("backgroundColor"));
                    cardFooterStyle.setPrimaryTextColor(cardFooterStyleObject.optString("primaryTextColor"));
                    cardFooterStyle.setPrimaryFontSize(cardFooterStyleObject.optString("primaryTextFontSize"));
                    cardFooter.setCardFooterStyle(cardFooterStyle);
                    card.setCardFooter(cardFooter);

                    //Set card to button object
                    button.setCard(card);
                    buttonsList.add(button);
                }
                buttonsArrayList.add(buttonsList);
            }

            buttonResponse.setButtonList(buttonsArrayList);

        }catch (JSONException je){
            Log.e(TAG, "getButtonResponse( " + je + ")");
        }

        return buttonResponse;
    }
}
