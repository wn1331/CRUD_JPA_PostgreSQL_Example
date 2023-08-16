import {Props} from "next/script";

export default function Layout(props: Props){
    return(
        <form>
            <h2>Delete</h2>
            {props.children}
        </form>
    )
}