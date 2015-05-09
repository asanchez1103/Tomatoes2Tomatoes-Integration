package t2t.tomatoes2tomatoes;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.w3c.dom.Text;

/**
 * Created by Alejandro on 5/8/2015.
 */
public class confirmPlay extends DialogFragment {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            int playerCount = 2; // variable for number of players, starts at 1

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View PopUp = layoutInflater.inflate(R.layout.confirm_select, null);
            final AlertDialog alertD = new AlertDialog.Builder(getActivity()).create();

            // get start, exit buttons
            ImageView btn1 = (ImageView) PopUp.findViewById(R.id.confirmButton);
            ImageView btn2 = (ImageView) PopUp.findViewById(R.id.cancelButton);


            // misc

            final TextView playerNum = (TextView) PopUp.findViewById(R.id.playersNum);

            // when start button is clicked
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), BoardActivity.class);
                    String currentVal = playerNum.getText().toString();
                    intent.putExtra("playerNum", currentVal);
                    startActivity(intent);
                }
            });

            // when exit button is clicked
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // nothing here yet
                }
            });

            // show pop up
            alertD.setView(PopUp);
            alertD.show();
            return alertD;
        }
}

