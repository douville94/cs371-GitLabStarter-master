package edu.up.cs371.textmod;

/**
 * class TextModActivity
 *
 * Allow text to be modified in simple ways with button-presses.
 *
 */
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import static edu.up.cs371.textmod.R.string.copy_name_button_text;
import java.util.ArrayList;

public class TextModActivity extends ActionBarActivity implements View.OnClickListener {

    private Button ClearButton;
    private TextView editText;
    private Button buttonUP;
    private String newText;
    private Button buttonDOWN;
    private Button reverseButton;
    private Spinner spinner;
    private Button copyButton;
    private Button spaceButton;
    private Button AlternatingButton;
    private Button punctuation;

    // array-list that contains our images to display
    private ArrayList<Bitmap> images;

    // instance variables containing widgets
    private ImageView imageView; // the view that shows the image

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // perform superclass initialization; load the layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_mod);

        reverseButton = (Button)findViewById(R.id.button4);
        editText = (EditText)findViewById(R.id.editText);
        buttonUP = (Button) findViewById(R.id.buttonUP);
        buttonDOWN = (Button) findViewById(R.id.buttonDOWN);
        ClearButton = (Button)findViewById(R.id.ClearButton);
        spinner = (Spinner)findViewById(R.id.spinner);
        copyButton = (Button)findViewById(R.id.button2);
        spaceButton = (Button)findViewById(R.id.noSpaceButton);

        punctuation = (Button)findViewById(R.id.punctuation);
        AlternatingButton = (Button)findViewById(R.id.AlternatingButton);


        reverseButton.setOnClickListener(this);
        buttonUP.setOnClickListener(new buttonUPListener());
        buttonDOWN.setOnClickListener(new buttonDOWNListener());
        ClearButton.setOnClickListener(this);
        copyButton.setOnClickListener(this);
        AlternatingButton.setOnClickListener(this);
        spaceButton.setOnClickListener(this);


        punctuation.setOnClickListener(this);

        // set instance variables for our widgets
        imageView = (ImageView)findViewById(R.id.imageView);

        // Set up the spinner so that it shows the names in the spinner array resources
        //
        // get spinner object
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        // get array of strings
        String[] spinnerNames = getResources().getStringArray(R.array.spinner_names);
        // create adapter with the strings
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, spinnerNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // bind the spinner and adapter
        spinner.setAdapter(adapter);

        // load the images from the resources
        //
        // create the arraylist to hold the images
        images = new ArrayList<Bitmap>();
        // get array of image-resource IDs
        TypedArray imageIds2 = getResources().obtainTypedArray(R.array.imageIdArray);
        // loop through, adding one image per string
        for (int i = 0; i < spinnerNames.length; i++) {
            // determine the index; use 0 if out of bounds
            int id = imageIds2.getResourceId(i,0);
            if (id == 0) id = imageIds2.getResourceId(0,0);
            // load the image; add to arraylist
            Bitmap img = BitmapFactory.decodeResource(getResources(), id);
            images.add(img);
        }

        // define a listener for the spinner
        spinner.setOnItemSelectedListener(new MySpinnerListener());

    }

//upper case listener
    private class buttonUPListener implements View.OnClickListener {

        public void onClick(View v) {
            newText = editText.getText().toString();
            editText.setText((newText.toUpperCase()));

        }
    }

//lower case listener
        private class buttonDOWNListener implements View.OnClickListener {

            public void onClick(View v) {
                newText = editText.getText().toString();//nathan
                editText.setText((newText.toLowerCase()));


            }

        }

//clear, reverse and copy button listener actions
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        String initText = editText.getText().toString();
        if (viewId == R.id.ClearButton)
        {
            editText.setText(" ");
        }
        else if (viewId == R.id.button4){
            String reverseText = reverse(initText);
            editText.setText(reverseText);
        }
        else if (viewId == R.id.button2){
            String spinText = spinner.getSelectedItem().toString();
            editText.setText(spinText);
        }
        else if (viewId == R.id.AlternatingButton){
            String x = initText;
            String y = "";
            for (int i = 0, len = x.length(); i < len; i++) {
                char ch = x.charAt(i);
                if (i % 2 == 0) {
                    y = y + (Character.toLowerCase(ch));
                } else {
                    y = y + (Character.toUpperCase(ch));
                }
            }

        }

        else if (viewId == R.id.punctuation){
            String words = removePunctuations(initText);
            editText.setText(words);
        }

        else if (viewId == R.id.noSpaceButton){
            String noSpaceText = initText.replaceAll("\\s+","");
            editText.setText(noSpaceText);
        }
    }
//
    public String removePunctuations(String s) {
        String res = "";
        for (Character c : s.toCharArray()) {
            if(Character.isLetterOrDigit(c))
                res += c;
        }
        return res;
    }

//lll
    //method to reverse text
    public static String reverse(String input){
        char[] in = input.toCharArray();
        int begin=0;
        int end=in.length-1;
        char temp;
        while(end>begin){
            temp = in[begin];
            in[begin]=in[end];
            in[end] = temp;
            end--;
            begin++;
        }
        return new String(in);
    }

    /**
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_text_mod, menu);
        return true;
    }

    /**
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * class that handles our spinner's selection events
     */
    private class MySpinnerListener implements OnItemSelectedListener {

        /**
         * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(
         *                  android.widget.AdapterView, android.view.View, int, long)
         */
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                   int position, long id) {
            // set the image to the one corresponding to the index selected by the spinner
            imageView.setImageBitmap(images.get(position));
        }

        /**
         * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(
         *                  android.widget.AdapterView)
         */
        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
            // your code here
        }
    }
}
