/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.luccasso.advent2022.day6;

import java.util.List;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Teresa
 */
public class TuningTest {

    String fullinput = "jfnjjwbbqttplpvllqgllmdllfmllscssqmqzmmwzznqnwqnwnqnjjbdbpbtbdbzzzljljzjjpccrmmppzfpzfpfnfccfbbcqcrcffblfbftbfbtbwwwmgwmgmnngnllnfllhghcghhjppchcfcnfffllmmqbmmpwwwwlqwwqgqcqsqjqpqzqqdzdtztltslsljjfqfcqqgbqqqghqgqvgvrggqwggrgjgmmnrmmzgmzgzpzjjctcmtcmcnndppcvpvrrwvrvhrvhrhjhnjnvjnjrjggccvffnqqvfqvvnmvmqmfmfqqzfzbfzzzgpzpllrwwnpwpnwnwgwhhrrdnrrdjjzjszsjjbddcdbbvmbmqbqnbqbsqbsqqwbwhwggssdnnmttvnnvmnmhmfhhjchcttzdzdqqszzcwwhhwzhwhphqhcqqsggddfmmvzmzwmwfwzwrrbmrrnwnfnwnlwnwrwfwnnmtnnzwnwdnnbhhrphrhlhwllpmmbcbtbffmqffddjnjwwzpzfpptbbqqwbwzbzjbjmjljblbtlblqqhqbqggrngrgllbmbccmhmqmqwwqcqssqzzfjzjrjnnqrqssfnsnvvtgvvmsvsqqljjbsbrrjllvfvzfzmzhzzhthjhshlslfljfjqjpqpvvmpmhpmhmqqmmdwmddppjlplhlsstlssgnggrbblggffcdfdzzwqqtztqtwqtwtzzsjsbszsbsvbvwwjqjnnpdpccwssvdsdzzqbqbtbtqtmtltltvlvddzwzzfpzpjpgphprpgpqqwppdwpdplddvffcdffvpvqqgvqgvvrfvrvqrrcjcpjjpttftqqvjqvqsvqsvqssdpdbbbmcmscsddbhhgttwhhjlltqllnqntqtsscnntwwhswswlwggldltlttsjszsnznsznzccbtbblplnnmfmqmrrvjvhjhzhnzzgnnhrrdrllblpbllfdfjjssvnssvlsllnqqhwqhhhsgstsjstthrrhrghrhhfmhmwhwrwwsrwrfwwdnntqnnsvnvmnnfvnntztqzqhqnqjnnjfflbfllrsllqhqdqccgvgnvvcwcfccmssqnqhhqrrfrtrvvnjnpjnjjpplmlppvmpphjhppvhvdvssjcjrrtrdrrsvvbbjzzrtztgzghzhccwmccshhzbhhdwdwsdswwlcwllpblpphrppfhfnffrbbcgcmggnvnzzmvvcrrftrftrffcscvcsslbljlglzgzbzczszmsmbmnbbhdhvvsqvqhvvfrfddbpwgvztwwqcpzhhwnhphnrwldjmztsptbbgsqbqqccwbdqzvhfjlfldgphzbfprclgpfztbrgvsvfpghmdchscbdqjqgzvmrtdrfzbhgdvgznjcsmglcfwhdtpsljnvvzjcbbrczwtgpdmgpzhctvbbmvsjzthffsjqhfsdrclpqslbhnmpczwvggpzbjcchfjzjhhgtrmlgnzlndfvzrccgggrpmprbmjbfjjhzrhrtwgqdbgdlqghssrnmtmpvttcqwnwdzhgfnddgbqcsdvzvwqdnmmpwrwhfbqtcpqhvwbczrmjqzsntvdrncwjsmvvwcngrtlwtjmnctwrrtvphbjhlqmgzfsfsrblzzvmzlbhzjhwbdfpncdrfchmrqhspdszcjrnvwtmjzmsmzcdphsdzjgqswwrpdvlpvrdnhplnlmswvcrzlcmbtqtscjfwrnrctrvdqcqzwcvgvpdgrndrgsrvzftwpqjjgjhzwhvrjlqntdtcjdrqzhqlqqdffcgvttlhvwgggnwmdlvghfgjpsmntbvbjbbttrwsljwsrvtmznvqdptpwtdcwtcsfdjlmdqthqggjcptrqhbsbjzqqmvvjmgmppqmjmnjdqvspzlbgzjsjshpslmszqnzghsszpsmpzfcrqqjdwvtbnzstvvjzvtzgpptcmvmbvmpvpzvgfnwtlmdzhvhshtwvnbgwmtzqhcptflpqsqvmptchpfcbwhvjzdcnsnqrgdwfcthqfssnbqnvgvvhlzqfqmdlcwnshtvhhhpghjbmhdbfbqcvbnbvwbzcbbmjnrqmsdqnmnbsrvhggzsrlbwtfmgwrnlhrbrrrqdcspnrpnppngrtdqtbmbhcbjrlhpfjpdnfndmqvwvhlgmsntpwrlrwwqhwvzbpzqqggnbqlsjjqtbqjcdpmndgmtdhfbqrpdzzsnmhzmqqnbdqftqmnhfbdzdlfwgjsjhrcsmtfzgwbvbbzdrlbmcgmppqfppmbqrnsmrmhrdsvgcfmzpfnvrbbgfccfcbphszwdbnnwcjjvvlpdtfzgtslvgqwmsvlpzjcbqwqclrjrsgthhtqrqrhvsdfjntgllsvslrvdtnsdmrgtqcmswnqwlrwlfmcfftbjpvdnmczqzldsssszhjtqtqvqtwhjcqchjvqvntvzzzprbmjcctsqfdcvpbtsgnnsqtqnmjhrgqcjnzrdsgrbtdpqjbgcmnfwhnsrfwcdmncjzwcngfbmmrsbvgvvqpvrdjfsqwjdmqjdpzcbjjfmzjjgbnwqgrvpmbzdhsgtldrzvglscfwbmjltcrzrgdslgprwscwbrhtdtglznjdcvfjzjjqzntdqdbcrcbbmvnzdshjzcsfsgpghmgdqdwsnwjtvtbqbqccbcwjpnhdhzcvdssvnvqtvzwprhpgftdwwvgsbnlzzjppcrrwmrsthvjjrvrsdrbdqfgsjsmwfplpstrbnpdhhcblhjfwzngmhlwbvnfcbgwshspsbbgbldrvmcnczszpgnddrfwrtgcqjggrrcbjwrdjlrvtspbftrtjbzjwchpfnjctcjtwtpmtblczcftqlphdjczfrvtzlsglpvhqsqqblttdjrlczhrqsgpggmvnhpqtrfbpgvzftwtsmwhwswtpvtwnsshmlcffpcjshqhqqsjtpbgszscmcbnhjjtjmpgfdhgmljqmmwlfptstjjvqhcbjpjpwzwqflhslclzzjlmcttbsncqmfzhgnzwbdtnvfwbtztwbhtfsqjfzwmfflmbwnqzqhcjwdpbvngsgzlwvwcqhqjsndznbbdcqqhmjjpqjbsnvwztgmqwdcbbjvcndmhsbvbjnzlbscmgnjcrrwrfdljtcsgmwtffgcjflpzzdcnzvmrbnrjbbmhzqqjtgsrwqmmrhpndwlbnrtrhhpqlmdrcrtdmzsslrmffpftdjvfcpvvhzhjhqtrrsclvtbsccgmmqrjbqgbmpnbzlsncssdhmjppjptvddfgbbnjzjjldjlqjzhhttsclrmsgzctwjqqvtjlfzwgtffgrdjzwdcnrprlcswffghngrqcgsbzqhhvbfjtwcjlrrmbtqjdrgpnbftnmzqnndnqwgrqndlwmjnnspbhjlnzrnptnrmcjhpbfcqpvbchvdwthjlcrfpssgtfbsgfrftcrwttrspbsvzpvcczmdqslcdgfljvtjsdpjnwmdvfzfllrdrbgvpltzlqcrlwbncswhfvrdthspmhfhfdlvpbcqlmjfznhnqblffftgzqrtswnmtnvjprqqhhhvrscvbbzgmnlnprghfdjqbgjppjzjrnclfdssbmgspwcscnlcrrqmtlljrmcwgdgcqwvvjzvsjdjvsspszlcthwzrwqtzdgmqvnlvvzrvrpqqwswzcchncrpnjdmflvmhhwvrrstpvnszfrmvpdtpqpbdmwvvbbpjnwmtststtlcvqdnvqqphzlhhzbbbjssgdcnhlmwrzwvwmcmgrcngqzcnffqzfnvldpdjmsspgpbrzhnszfnljfcrgsjvqjjbstvghlcslhqlzhltpglwffrzfgjghssfgrptbnpbhqnhhfbjsnmsvltqpthdmzzrhrhhmzlplvrtdqfrfrppdpqnllblcfjqpdwznsbrhcncdpmztcrjrfnlwtznrmpbzqsbrqrbnthgfpshrdhnwjmrnsmsfqwdjsmsvhfrbdpjrwcvmdvvmdtfqjgmdsrqtctsdmznngbsrfjvhllgwt";

    List<String> test = List.of(
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb",
            "bvwbjplbgvbhsrlpgdmjqwftvncz",
            "nppdvjthqldpwncqszvftbrmjlhg",
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"
    );

    @Test
    public void testPart1Test() {
        int[] result = test.stream()
                .mapToInt(Tuning::findStart)
                .toArray();
        assertThat(result).containsExactly(7, 5, 6, 10, 11);
    }

    @Test
    public void testPart1Full() {
        assertThat(Tuning.findStart(fullinput)).isEqualTo(1300);
    }

    @Test
    public void testPart2Test() {
        int[] result = test.stream()
                .mapToInt(Tuning::findMessage)
                .toArray();
        assertThat(result).containsExactly(19, 23, 23, 29, 26);
    }

    @Test
    public void testPart2Full() {
        assertThat(Tuning.findMessage(fullinput)).isEqualTo(3986);
    }

}
