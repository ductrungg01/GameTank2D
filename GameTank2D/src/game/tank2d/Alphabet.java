package game.tank2d;

import pkg2dgamesframework.Objects;

enum TypeOfAlphabet {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8),
    I(9),
    J(10),
    K(11),
    L(12),
    M(13),
    N(14),
    O(15),
    P(16),
    Q(17),
    R(18),
    S(19),
    T(20),
    U(21),
    V(22),
    W(23),
    X(24),
    Y(25),
    Z(26),
    ZERO(27),
    ONE(28),
    TWO(29),
    THREE(30),
    FOUR(31),
    FIVE(32),
    SIX(33),
    SEVEN(34),
    EIGHT(35),
    NINE(37),
    DOT(38), //.
    COMMA(39), //,
    FSLASH(40), // /
    BSLASH(41), // \
    STRAIGHT(42), // |
    DASH(43), // -
    MORE(44), // >
    LESS(45), // <
    ENEMY(46),
    ALLY(47);


    int type;
    TypeOfAlphabet(int i) {
        this.type = i;
    }

    public int getType() {
        return type;
    }

}

public class Alphabet extends Objects {
    public static final int ALPHABET_WIDTH = 16;
    public static final int ALPHABET_HEIGHT = 16;
    private static final State DEFAULT_STATE = State.IDLE;
    private static final Rotation DEFAULT_ROTATION = Rotation.UP;
    private TypeOfAlphabet type;

    Alphabet(TypeOfAlphabet type, int x, int y){
        super(x, y, ALPHABET_WIDTH, ALPHABET_HEIGHT, 0, DEFAULT_STATE, DEFAULT_ROTATION);

        this.type = type;

        int yOnImg = 0;
        int xOnImg = 0;

        // region case
        switch (type){
            case ENEMY -> {
                xOnImg = 48;
                yOnImg = 272;
                break;
            }
            case ALLY -> {
                xOnImg = 32;
                yOnImg = 272;
                break;
            }
            case A -> {
                xOnImg = 16 * 0;
                yOnImg = 992;
                break;
            }
            case B -> {
                xOnImg = 16 * 1;
                yOnImg = 992;
                break;
            }
            case C -> {
                xOnImg = 16 * 2;
                yOnImg = 992;
                break;
            }
            case D -> {
                xOnImg = 16 * 3;
                yOnImg = 992;
                break;
            }
            case E -> {
                xOnImg = 16 * 4;
                yOnImg = 992;
                break;
            }
            case F -> {
                xOnImg = 16 * 5;
                yOnImg = 992;
                break;
            }
            case G -> {
                xOnImg = 16 * 6;
                yOnImg = 992;
                break;
            }
            case H -> {
                xOnImg = 16 * 7;
                yOnImg = 992;
                break;
            }
            case I -> {
                xOnImg = 16 * 8;
                yOnImg = 992;
                break;
            }
            case J -> {
                xOnImg = 16 * 9;
                yOnImg = 992;
                break;
            }
            case K -> {
                xOnImg = 16 * 10;
                yOnImg = 992;
                break;
            }
            case L -> {
                xOnImg = 16 * 11;
                yOnImg = 992;
                break;
            }
            case M -> {
                xOnImg = 16 * 12;
                yOnImg = 992;
                break;
            }
            case N -> {
                xOnImg = 16 * 13;
                yOnImg = 992;
                break;
            }
            case O -> {
                xOnImg = 16 * 14;
                yOnImg = 992;
                break;
            }
            case P -> {
                xOnImg = 16 * 15;
                yOnImg = 992;
                break;
            }
            case Q -> {
                xOnImg = 16 * 16;
                yOnImg = 992;
                break;
            }
            case R -> {
                xOnImg = 16 * 17;
                yOnImg = 992;
                break;
            }
            case S -> {
                xOnImg = 16 * 18;
                yOnImg = 992;
                break;
            }
            case T -> {
                xOnImg = 16 * 19;
                yOnImg = 992;
                break;
            }
            case U -> {
                xOnImg = 16 * 20;
                yOnImg = 992;
                break;
            }
            case V -> {
                xOnImg = 16 * 21;
                yOnImg = 992;
                break;
            }
            case W -> {
                xOnImg = 16 * 22;
                yOnImg = 992;
                break;
            }
            case X -> {
                xOnImg = 16 * 23;
                yOnImg = 992;
                break;
            }
            case Y -> {
                xOnImg = 16 * 24;
                yOnImg = 992;
                break;
            }
            case Z -> {
                xOnImg = 16 * 25;
                yOnImg = 992;
                break;
            }
            case ZERO -> {
                xOnImg = 16 * 0;
                yOnImg = 1008;
                break;
            }
            case ONE -> {
                xOnImg = 16 * 1;
                yOnImg = 1008;
                break;
            }
            case TWO -> {
                xOnImg = 16 * 2;
                yOnImg = 1008;
                break;
            }
            case THREE -> {
                xOnImg = 16 * 3;
                yOnImg = 1008;
                break;
            }
            case FOUR -> {
                xOnImg = 16 * 4;
                yOnImg = 1008;
                break;
            }
            case FIVE -> {
                xOnImg = 16 * 5;
                yOnImg = 1008;
                break;
            }
            case SIX -> {
                xOnImg = 16 * 6;
                yOnImg = 1008;
                break;
            }
            case SEVEN -> {
                xOnImg = 16 * 7;
                yOnImg = 1008;
                break;
            }
            case EIGHT -> {
                xOnImg = 16 * 8;
                yOnImg = 1008;
                break;
            }
            case NINE -> {
                xOnImg = 16 * 9;
                yOnImg = 1008;
                break;
            }
            case DOT -> {
                xOnImg = 16 * 11;
                yOnImg = 1008;
                break;
            }
            case COMMA -> {
                xOnImg = 16 * 12;
                yOnImg = 1008;
                break;
            }
            case FSLASH -> {
                xOnImg = 16 * 13;
                yOnImg = 1008;
                break;
            }
            case BSLASH -> {
                xOnImg = 16 * 14;
                yOnImg = 1008;
                break;
            }
            case STRAIGHT -> {
                xOnImg = 16 * 15;
                yOnImg = 1008;
                break;
            }
            case DASH -> {
                xOnImg = 16 * 16;
                yOnImg = 1008;
                break;
            }
            case MORE -> {
                xOnImg = 16 * 17;
                yOnImg = 1008;
                break;
            }
            case LESS -> {
                xOnImg = 16 * 18;
                yOnImg = 1008;
                break;
            }

        }
        // endregion
        this.isNotGameObject = true;
        super.setAnimation(100, xOnImg, yOnImg, ALPHABET_WIDTH, ALPHABET_HEIGHT);
    }
    public void update(long deltaTime){
        this.getAnimation().Update_Me(deltaTime);
    }
    //region Getter and Setter
    public TypeOfAlphabet getType() {
        return type;
    }

    public void setType(TypeOfAlphabet type) {
        this.type = type;
    }
//endregion
}
