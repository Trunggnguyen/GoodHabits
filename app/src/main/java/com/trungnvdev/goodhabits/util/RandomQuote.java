/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.trungnvdev.goodhabits.util;


public class RandomQuote {
    public static String getQuote(int count){
    String[] arr = { "Đừng chờ đợi những gì bạn ước muốn mà hãy đi tìm kiếm chúng" ,
            "Dù cuộc đời có đáng thất vọng đến mức nào, thì niềm hi vọng vẫn luôn ở phía trước" ,
            "Dù bạn có vấp ngã hàng trăm lần thì cũng đừng bỏ cuộc. Hãy đứng dậy!" ,
            "Tìm ra mục đích sống của bạn là bước quan trọng đầu tiên để sống một cuộc sống không giới hạn",
            "Nơi nào có ý chí và có những con sóng, thì nơi đó có cách để lướt sóng",
            "Không có con đường nào dài quá đối với kẻ bước đi thong thả, không vội vàng. Không có cái lợi nào xa xôi quá đối với những kẻ kiên nhẫn làm việc",
            "Cuộc sống vẫn vậy nếu nó lấy đi thứ gì của bạn, thì thế nào nó cũng bù lại cho bạn thứ khác, chỉ có điều là bạn có chịu đi tìm hay không thôi",
            "Khi bạn cảm thấy mình thất bại và bị lấn át bởi một thách thức lớn, hãy tin rằng không gì là không thể",
            "Hãy theo đuổi đam mê, thành công sẽ theo đuổi bạn\n",
            "Tôi chưa bao giờ thực sự tàn tật cho đến khi tôi mất hy vọng. Hãy tin tôi đi, mất hy vọng còn tồi tệ hơn cả mất chân tay",
            "Bạn có thể tin vào những giấc mơ của mình, nhưng bạn phải hành động để biến những giấc mơ ấy thành hiện thực",
            "Những thách thức trong cuộc sống là để làm vững mạnh thêm niềm tin của chúng ta. Chúng không phải để vùi dập chúng ta",
            "Nếu bạn không nhận được một điều kỳ diệu, hãy tự mình trở thành một điều kỳ diệu",
            "Nghịch cảnh là một phần của cuộc sống. Nó không thể bị kiểm soát. Cái có thể kiểm soát chính là cách chúng ta phản ứng trước nghịch cảnh",
            "Người lạc quan luôn nhìn thấy thành công trong mỗi khó khăn, còn người bi quan luôn thấy rủi ro trong mỗi cơ hội",
            "Có nỗ lực mới có hy vọng, có ý chí mới có thành quả. Nếu bạn chẳng dám làm việc gì thì đến cuối đời trong bạn chỉ có sự ân hận mà thôi",
            "Hạnh phúc hay khổ đau ai cũng sẽ phải nếm trải. Điều quan trọng là ta có biết học cách chấp nhận để mà vươn lên hay không thôi",
            "khi cuộc sống trở nên khắc nghiệt, hãy coi đó là cơ hội để chứng minh sức mạnh của bản thân",
            "Sẽ không bao giờ có bế tắc thật sự nếu bạn còn niềm tin. Chỉ cần có niềm tin, bạn sẽ tìm thấy hi vọng và con đường để bước tiếp",
            "Lúc này nếu ngủ bạn sẽ có một giấc mơ. Nhưng lúc này nếu học bạn sẽ giải thích được giấc mơ",
            "Sẽ không bao giờ có bế tắc thật sự nếu bạn còn niềm tin. Chỉ cần có niềm tin, bạn sẽ tìm thấy hi vọng và con đường để bước tiếp",
            "Không phải người ta ngừng theo đuổi giấc mơ vì mình già đi, người ta già đi vì ngừng theo đuổi giấc mơ",
            "Sửa mình làm cung, uốn ý tưởng làm tên, lấy nghĩa vững làm đích, ngắm cho ngay rồi bắn ra, bắn ra tất phải trúng đích",
            "Chìa khóa thành công là tập trung lý trí của chúng ta vào những điều chúng ta muốn chứ không phải những điều chúng ta sợ",
            "Lời giả dối làm rối loạn tâm thiện. Không nhịn được điều nhỏ nhặt sẽ làm hư chuyện đại sự",
            "Ăn mừng thành công cũng tốt, nhưng quan trọng hơn là phải biết chú ý đến những bài học của sự thất bại",
            "Thành công là một người thầy tồi tệ. Nó quyến rũ những người thông minh vào ý nghĩ rằng họ sẽ chẳng bao giờ thất bại",
            "Đừng mong đích đến sẽ thay đổi nếu như bạn không thay đổi con đường",
            "Chúng tôi sẽ làm tất cả để thành công. Đơn giản bởi chúng tôi là những người trẻ và chúng tôi không bao giờ biết từ bỏ",
            "Mọi công việc thành đạt đều nhờ sự kiên trì và lòng say mê\n",
            "Thành đạt không phải do sự giúp đỡ của người khác mà chính do lòng tự tin",
            "Muốn thành công thì khao khát thành công phải lớn hơn nỗi sợ bị thất bại\n",
            "Mức độ thành công được xác định không phải bởi những gì ta đã đạt được, mà bởi những trở ngại ta đã vượt qua",
            "Bạn càng thành công thì ở gần bạn càng ít người vui vì sự thành công của bạn",
            "Thành công của chúng ta không phải là những gì mà ta đang sở hữu mà là những gì ta sẽ để lại",
            "Người bị vấp ngã là người dám liều mình. Qua cách họ đối phó với sai lầm, ta có thể đoán dược cách họ giải quyết khó khăn trong tương lai",
            "Đời là cuộc đấu tranh liên tục; nó luôn được cải biên với nhưng khó khăn mới. Và chúng ta sẽ chiến thắng nhưng bao giờ cũng phải trải giá",
            "Đường đi khó không phải vì ngăn sông cách núi . Mà khó vì lòng người ngại núi e sông",
            "Muốn cầu tiến hơn người, ra đời phải biết ngước mặt nhìn lên. Vì nhìn xuống ta thấy hơn người, nhưng nhìn lên ta chỉ là con số không vĩ đại",
            "Đường tuy gần không đi không bao giờ đến, việc tuy nhỏ không làm chẳng bao giờ nên",
            "Tôi chọn người lười biếng để làm những việc khó khăn. Bởi một người lười biếng sẽ tìm ra cách dễ dàng để làm việc đó",
            "Nếu bạn sinh ra trong nghèo khó, đó không phải là lỗi của bạn. Nhưng nếu bạn chết trong nghèo khó, thì đó là lỗi của bạn",
            "Không ai quan tâm đến lòng tự trọng của bạn đâu. Mọi người chỉ trông đợi bạn đạt được điều gì đó trước khi bạn cảm thấy hài lòng về bản thân",
            "Nếu bạn không bỏ cuộc bạn sẽ còn cơ hội\n",
            "Người thành công không bao giờ phàn nàn\n",
            "Nếu bạn muốn phát triển, hãy nhìn vào thời cơ\n",
            "Con người cần có chỉ số tình yêu để thành công\n",
            "Điều quan trọng nhất mà bạn nên có đó là sự kiên nhẫn\n",
            "Nếu chúng ta muốn thay đổi thế giới. Hãy thay đổi bản thân trước\n",
            "Đội của bạn sẽ thành công khi có ai đó có góc nhìn khác lạ dẫn dắt\n",
            "Nếu bạn bé nhỏ, thì nên tập trung vào trí tuệ chứ không phải thể lực\n",
            "Tôi thất bại rồi làm lại, thất bại rồi làm lại cho đến khi tôi thành công",
            "Nếu bạn không chịu bắt tay vào làm thì không có điều gì là khả thi cả\n",
            "Nếu bạn đối xử với mọi người như kẻ thù, họ sẽ trở thành kẻ thù của bạn",
            "Không có ai là siêu anh hùng. Nếu bạn muốn thành công, bạn phải thực tế",
            "Điều quan trọng nhất không phải là tiền mà là những người có cùng ý tưởng",
            "Thất bại càng nhiều càng chứng tỏ bạn còn cách thành công không xa nữa đâu",
            "Rất nhiều người trên thế gian này có thể làm bạn, nhưng không phải làm đối tác",
            "Không phải mình bạn làm cả team thành công, mà cả team khiến bạn thành công",
            "Hãy tin và yêu công việc bạn đang làm cho dù có những người thích hoặc không thích nó",
            "Ngày mai có thật sự huy hoàng tuỳ thuộc vào sự lựa chọn và hành động của bạn hôm nay",
            "Có 3 yếu tố thành công: Đúng thời điểm, đúng người và chuẩn bị cho chặng đường 10 năm",
            "Đừng thất vọng khi bị từ chối, đừng buồn khi mình thất bại, đây chỉ là bài test của cuộc đời thôi",
            "Thất bại với tôi không quan trọng, quan trọng là tôi sẽ truyền những gì học được cho người khác",
            "Người được gọi là Quý nhân là Người mở mang tầm nhìn của bạn, đưa bạn đến với 1 thế giới mới",
            "Chúng ta học được rất ít từ thành công của người khác, nhưng sẽ học được rất nhiều từ thất bại của họ",
            "Nếu những quyết định của bạn khác biệt so với những gì mà bạn bè làm, cuộc sống của bạn cũng sẽ khác",
            "Khi bạn đang 25 tuổi, hãy cứ sai lầm đi, đừng lo lắng gì cả. Ngã thì đứng dậy, thất bại thì lại vùng lên thôi",
            "Nếu các bạn là một tập thể mạnh, biết mình muốn làm gì, một người trong đội có thể đánh bại mười người",
            "Khi người ta nói “cái này”, bạn phải hỏi mình “Tại sao không phải là cái kia?”. Bạn phải trở nên khác biệt",
            "Hôm nay thật khắc nghiệt, ngày mai còn khắc nghiệt hơn, nhưng nếu bạn cố gắng, ngày kia sẽ là ngày tươi sáng",
            "Nếu bạn muốn làm kinh doanh tốt bạn cần có một team tốt, hãy tìm người phù hợp đừng tìm người giỏi nhất",
            "Bạn có thể học hỏi từ đối thủ cạnh tranh, nhưng đừng bao giờ sao chép. Nếu bạn sao chép từ đối thủ, bạn sẽ thua",
            "Đừng khiến nhân viên suy nghĩ đang đi làm để phục vụ cho lợi ích của lãnh đạo. Hãy hướng họ tới một mục tiêu chung",
            "Chúng ta không nên e ngại thất bại, chúng ta nên tận hưởng thất bại và những khoảnh khắc không như ý để đứng lên từ đó",
            "Chúng ta phải học từ sai lầm của người khác, chứ không phải lúc nào cũng từ câu chuyện thành công. Chúng ta phải biết vì sao họ thất bại",
            "May mắn lớn nhất của cuộc đời không phải nhặt được tiền cũng không phải trúng số mà là có người có thể dẫn bạn đi đến 1 nền tảng cao hơn",
            "Thà làm một bông hoa sen nở khi thấy mặt trời bị mất hết nhụy còn hơn giữ nguyên hình nụ búp trong sương lạnh vĩnh cửu của mùa đông",
            "Thay đổi sẽ không đến nếu chúng ta trông chờ vào người khác hay đợi thời điểm khác. Chúng ta chỉ là sự thay đổi mà chúng ta đang tìm kiếm",
            "Giấc mơ không phải là thứ bạn nhìn thấy khi ngủ, giấc mơ là những điều mà không cho phép bạn ngủ",
            "Trong thời đại này, trừ khi bị lũ lụt cuối trôi hết tài sản, còn nếu bạn nghèo là do bạn lười",
            "Thời gian một đi là không trở lại, không ai có thể tắm hai lần trên dòng sông cuộc đời",
            "Khi bạn có tiền trong tay chỉ có bạn quên mất mình là ai. Nhưng khi bạn không có đồng nào cả, cả thế giới sẽ quên đi bạn là ai",
            "Con người sinh ra không phải để tan biến như một hạt cát vô danh. Họ sinh ra là để in dấu trên mặt đất, in dấu trong tim người khác",
            "Tuổi trẻ là tuổi của tương lai. Muốn có tương lai tốt đẹp thì phải chiếm lấy bằng ý chí và nghị lực của chính bản thân",
            "Khám phá vĩ đại nhất của thời đại chúng ta chính là việc con người có thể thay đổi cuộc sống của mình chính bằng cách thay đổi thái độ sống",
            "Nỗ lực của một người tốt vô danh cũng như mạch nước ngầm sâu dưới đất, thầm lặng làm mặt đất xanh tươi",
            "Người hạnh phúc không phải là người được sống trong hoàn cảnh thuận lợi nào mà là một người có thái độ sống tốt trước bất kỳ hoàn cảnh nào",
            "Thành công sẽ đến với những ai biết rõ mình muốn điều gì và không bao giờ bỏ cuộc cho đến khi đạt được điều đó",
            "Một người không bao giờ phạm sai lầm vì họ không bao giờ thử làm những điều mới",
            "Tôi cho rằng, một nửa sự khác biệt giữa những doanh nhân thành công và không thành công đến từ sự kiên trì thuần khiết" +
            "Hãy xây lên giấc mơ của riêng bạn, nếu không người khác sẽ thuê bạn xây dựng giấc mơ của họ đó",
            "Trong một thế giới đang thay đổi rất nhanh chóng, lộ trình duy nhất đưa bạn đến thất bại là không dám mạo hiểm",
            "Nếu bạn không chịu bắt tay vào làm thì chẳng có điều gì là khả thi cả",
            "Cách để bắt đầu đó là ngừng nói suông và bắt tay vào công việc",
            " Nếu bạn sinh ra trong nghèo khó đó không phải là lỗi của bạn. Nhưng nếu bạn chết trong nghèo khó thì đó là lỗi của bạn",
            "Kẻ khốn cùng nhất trên thế giới này không phải là những người không một xu dính túi, mà là kẻ không có nổi một ước mơ",
            "Không có áp lực, không có kim cương\n"};
        //Log.d("TAG", "getQuote: " + arr.length);
        return arr[count]+".";
    }
}
