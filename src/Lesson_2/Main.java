package Lesson_2;

public class Main {
    static String[][] testArrayOne = {
            {"1", "1", "1", "1",},
            {"1", "1", "1", "1",},
            {"1", "1", "1", "1",},
            {"1", "1", "1", "1",}
    };

    public static void main(String[] args) {
        try {
            System.out.println(new Main().sumOfStrArr(testArrayOne));
        } catch (MyArraySizeException eSize) {
            eSize.printStackTrace();
        } catch (MyArrayDataException eData) {
            eData.printStackTrace();
        }

    }

    int sumOfStrArr(String[][] arrTwoDim) {
        int sum = 0;
        if (arrTwoDim.length != 4) throw new MyArraySizeException();

        for (String[] arrOneDim : arrTwoDim) {
            if (arrOneDim.length != 4) throw new MyArraySizeException();
            for (String strElem : arrOneDim) {
//                if (Integer.parseInt(strElem) instanceof int)
                sum += Integer.parseInt(strElem);
            }
        }

        return sum;
    }
}

class MyArraySizeException extends ArrayIndexOutOfBoundsException {

}

class MyArrayDataException extends NumberFormatException {

}
