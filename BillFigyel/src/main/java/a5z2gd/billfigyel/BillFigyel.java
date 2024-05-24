package a5z2gd.billfigyel;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BillFigyel implements NativeKeyListener
    {
    private boolean ctrlPressed = false;
    private boolean altPressed = false;

    public static void main(String[] args)
        {
        
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        logger.setUseParentHandlers(false);

        try
            {
            GlobalScreen.registerNativeHook();
            }
        catch (NativeHookException e) 
            {
            e.printStackTrace();
            System.exit(1);
            }

        GlobalScreen.addNativeKeyListener(new BillFigyel());
        }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e)
        {
        if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL)
            {
            ctrlPressed = true;
            }
        else if (e.getKeyCode() == NativeKeyEvent.VC_ALT) 
            {
            altPressed = true;
            }
        else if (e.getKeyCode() == NativeKeyEvent.VC_C && ctrlPressed && altPressed)
            {
            typeString("Benyomtad a control alt c-t");
            }
        else if (e.getKeyCode() == NativeKeyEvent.VC_V && ctrlPressed && altPressed)
            {
            typeString("Benyomtad a control alt v-t");
            }
        }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) 
        {
        if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) 
            {
            ctrlPressed = false;
            } 
        else if (e.getKeyCode() == NativeKeyEvent.VC_ALT) 
            {
            altPressed = false;
            }
        }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) 
        {
        
        }

    private void typeString(String text)
        {
        try
            {
            Robot robot = new Robot();
            for (char c : text.toCharArray()) 
                {
                int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
                if (KeyEvent.CHAR_UNDEFINED == keyCode)
                    {
                    throw new RuntimeException(
                            "Key code not found for character '" + c + "'");
                    }
                robot.keyPress(keyCode);
                robot.keyRelease(keyCode);
                }
            }
        catch (Exception e) 
            {
            e.printStackTrace();
            }
        }
    }