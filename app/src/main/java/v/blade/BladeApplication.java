package v.blade;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;
import org.acra.ACRA;
import org.acra.config.CoreConfigurationBuilder;
import org.acra.config.HttpSenderConfigurationBuilder;
import org.acra.config.ToastConfigurationBuilder;
import org.acra.data.StringFormat;
import org.acra.sender.HttpSender;

public class BladeApplication extends Application
{
    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        CoreConfigurationBuilder builder = new CoreConfigurationBuilder(this);
        builder.setBuildConfigClass(BuildConfig.class)
                .setReportFormat(StringFormat.JSON);
        builder.getPluginConfigurationBuilder(ToastConfigurationBuilder.class)
                .setResText(R.string.oncrash)
                .setLength(Toast.LENGTH_LONG).setEnabled(true);
        builder.getPluginConfigurationBuilder(HttpSenderConfigurationBuilder.class)
                .setUri("http://valou3433.fr:5984/acra-blade/_design/acra-storage/_update/report")
                .setBasicAuthLogin("REPORTER")
                .setBasicAuthPassword("thereporterpassword")
                .setHttpMethod(HttpSender.Method.PUT)
                .setEnabled(true);
        ACRA.init(this, builder);
    }
}
