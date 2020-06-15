package com.example.conversationlisting.utilities
import java.util.Date
import java.text.SimpleDateFormat


class DateFormatConverter
{
    companion object
    {
        fun GetDate(userDate:String):String?
        {
            var now:Date
            var output:String
            var formatter =SimpleDateFormat("dd-MM-yyyy");

            try
            {
                now = SimpleDateFormat("yyyy-MM-dd HH:mm").parse(userDate);

            }
            catch(ex:Exception)
            {

                try
                {
                    now = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(userDate);
                }
                catch(ex:Exception)
                {
                    try
                    {
                        now = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(userDate);


                    }
                    catch(ex:Exception)
                    {
                        try
                        {
                            now = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'").parse(userDate);

                        }
                        catch(ex:Exception)
                        {
                            try
                            {
                                now = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(userDate);
                            }
                            catch(ex:Exception)
                            {
                                output="Invalid Format"
                                return output;
                            }

                        }
                    }

                }

            }
            output=formatter.format(now)
            return output

        }


    }

}