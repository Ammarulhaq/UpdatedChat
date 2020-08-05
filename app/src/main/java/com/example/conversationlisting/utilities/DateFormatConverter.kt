package com.example.conversationlisting.utilities
import java.text.SimpleDateFormat
import java.util.*


class DateFormatConverter
{
    companion object
    {
        fun GetDate(userDate:String,format: SimpleDateFormat):String?
        {
            var now:Date

            var output:String
            var formatter =format

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
                                try
                                {
                                    now=SimpleDateFormat("yyyy-MM-dd").parse(userDate)
                                }
                                catch (ex:java.lang.Exception)
                                {
                                    output="Invalid Format"
                                    return output;

                                }


                            }

                        }
                    }

                }

            }
            output=formatter.format(now)
            return output

        }

        fun StringtoDateConversion(userDate:String):Date
        {
           return SimpleDateFormat("dd-MM-yyyy").parse(userDate)

        }
        fun StringtoMinConversion(userDate:String):Date
        {
            return SimpleDateFormat("hh:mm a").parse(userDate)

        }



        fun GetDays(d1:String,d2:String,Day:Boolean):Long
        {

            if(Day)
            {
                var latestDate:Date=StringtoDateConversion(d1)

                var pastDate:Date=StringtoDateConversion(d2)

                var diff:Long=latestDate.getTime()-pastDate.getTime()


                var seconds:Long=diff/1000

                var minutes :Long = seconds / 60;

                var hours:Long = minutes / 60;

                var days:Long = hours / 24;

                return days;
            }



            else
            {
                var latestDate:Date= StringtoMinConversion(d1)

                var pastDate:Date=StringtoMinConversion(d2)

                var diff:Long=latestDate.getTime()-pastDate.getTime()


                var seconds:Long=diff/1000

                var minutes :Long = seconds / 60;

                var hours:Long = minutes / 60;

                var days:Long = hours / 24;





                return minutes;
            }


        }


        fun formatDateToString(date: Date?, format: String?, timeZone: String?
        ): String? {
            // null check
            var timeZone = timeZone
            if (date == null) return null
            // create SimpleDateFormat object with input format
            val sdf = SimpleDateFormat(format)
            // default system timezone if passed null or empty
            if (timeZone == null || "".equals(timeZone.trim { it <= ' ' }, ignoreCase = true)) {
                timeZone = Calendar.getInstance().getTimeZone().getID()
            }
            // set timezone to SimpleDateFormat
            sdf.timeZone = TimeZone.getTimeZone(timeZone)
            // return Date in required format with timezone as String
            return sdf.format(date)
        }






    }

}