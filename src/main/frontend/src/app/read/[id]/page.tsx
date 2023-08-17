import axios from "axios";

export default function Read(props){
    const {property} = props;
    return (
        <>
            <h2>Read!!</h2>
            <div >
                {property||"불러오는 중..."}
            </div>
        </>

    )
}
// export async function getServerSideProps(){
//     const response = await axios.get("/")
// }